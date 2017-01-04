package bs;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base.menge.e.EElem;
import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.menge.e.EDraw;
import base.p.Wahr;
import base.structure.Tupel;
import base.verteilung.Laplace;
import base.zv.Px;

public class WurfelTest {
	StichProbe wurfel;
	@Test
	public void test() {
		int n = 6;
		wurfel = new StichProbe(n,1,true, new Laplace(n));
		EreignisMenge grade = wurfel.draw(t -> { return t.get(0) % 2 ==0 ;});
		EreignisMenge zwei = wurfel.draw(t -> { return t.get(0)  ==2 ;});
		
		//Wahrscheinlich wahr = new Lap(wurfel, new Laplace(wurfel.Macht));
		
		if(grade.p()!= 0.5)
			fail();
		if(zwei.p()!= 1./6.)
			fail();
		
		EreignisMenge E_immerGrade = wurfel.draw(3, (Tupel t) -> { 
			for(int i : t){
				if(i % 2 ==1 ){
					return false;
				}
			}
			return true;
		});
		Assertion.Assert(E_immerGrade.p() , (1./8.));
	
		int summe = 7;
		EreignisMenge E_augenSumme = wurfel.draw(2,(Tupel t) -> { 
			int count = 0;
			for(int i : t){
				count +=i+1;
			}
			if(t.n<2){
				return true;
			}
			return count == summe;
		});
		if(!Wahr.eq(E_augenSumme.p(),6./36.))
			fail();
		int m=6;
		EreignisMenge E_jeder1Mal = wurfel.draw(m,t ->{
			boolean[] seiten  = new boolean[6];
			for(int i : t){
				seiten[i] = true;
			}
			int fehlen = 0;
			for(boolean b : seiten){
				if(!b) fehlen ++;
			}
			if(fehlen == 0) return true;
			if(t.n + fehlen <= m) return true;
			return false;
		});
		//System.out.println(E_jeder1Mal.p());
		wurfel.setTimes(2);
		Px X = new Px(wurfel, t -> {
			if(t.n == 1){
				return (double) t.get(0);
			}
			if(t.n == 2){
				return (double) (t.get(0)>t.get(1)?t.get(0):t.get(1));
			}
			return 0.;
		});
		if(X.dichte(4.) != 0.25000000000000006){
			fail();
		}
		if(X.dichte(0.) != 0.027777777777777776){
			fail();
		}
		if(Px.Erwartungswert(X)!=3.472222222222223){
			fail();
		}
		if(Px.Varianz(X)!=1.9714506172839474){
			fail();
		}
		/*
		for(Double x : X){
			System.out.println("f"+x + ":  "+ X.dichte(x));
		}
		System.out.println("E[X]:  "+ Px.Erwartungswert(X));
		System.out.println("Var[X]:  "+ Px.Varianz(X));
		*/
	}
	
	

}

