package bs;
 import static bs.Assertion.Assert;

import java.util.function.Function;

import org.junit.Test;

import base.menge.Menge;
import base.menge.e.EDraw;
import base.menge.e.Ereignis;
import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.structure.Linear;
import base.structure.Tupel;
import base.verteilung.VCreaterZweig;
import base.verteilung.Verteilung;
public class Nachricht_Test {
	StichProbe nachricht ;
	@Test
	public void test(){
		int n = 10;
		double sentCorrectly = 0.9;
		
		VCreaterZweig vc = new VCreaterZweig(n,2);
		vc.addTupel(Tupel.create(0,n-1), sentCorrectly);
		for(int i =1;i<n;i++){
			vc.addTupel(Tupel.create(i,i), sentCorrectly);
		}
		Verteilung old = new Verteilung(){

			@Override
			public double p(Tupel t) {
				if(t.n>2)return 0;
				if(t.n<1) return 0;
				if(t.n==1) return p(t.get(0));
				if(t.get(0)==t.get(1))
					return sentCorrectly/n;
				double all = (n * n)-n;
				return (1.-sentCorrectly) / all;
			}

			@Override
			public double p(int i) {
				return 1./(double)n;
			}
			
		};
		
		Verteilung VCreated = vc.getV();
		nachricht = new StichProbe(n,2, true, VCreated);
		Ereignis all = ENachricht( t ->true);
		Assert(all.p(),1.);
		EreignisMenge S0 = ENachricht(t -> t.get(0) == 0) ;
		EreignisMenge E1 = ENachricht(t -> t.n ==2 ? t.get(1) == 1 : true) ;
		Assert(S0.p(),1./n);
		Assert(E1.p(),1./n);
		Assert(Menge.BEDINGUNG(E1, S0).p(), (1.-sentCorrectly)/(double)(n-1));
		Assert(Menge.BEDINGUNG(S0, E1).p(), (1.-sentCorrectly)/(double)(n-1));
		

		Verteilung aufgabe3_6 = new Verteilung(){

			@Override
			public double p(Tupel t) {
				if(t.n == 1){
					return p(t.get(0));
				}
				if(t.get(0) == t.get(1))
					return 0.81	*p(t.get(0));
				if(t.get(0) + t.get(1) == 3){
					return 0.01*p(t.get(0));
				}
				return 0.09*p(t.get(0));
			}

			@Override
			public double p(int i) {
				switch(i){
				case 0: return 0.9;
				case 1: return 0.06;
				case 2: return 0.03;
				case 3: return 0.01;
				}
				return 0;
			}
			
		};
		

		nachricht = new StichProbe(4,2, true, aufgabe3_6);
		all = ENachricht( t ->true);
		Assert(all.p(),1.);
		for(int i = 0; i < 4; i++){
			for(int j = 0 ; j <4;j++){
				int w = i;
				int v = j;
				EreignisMenge Ew = ENachricht(t ->t.n ==2 ? t.get(1) == w : true);
				//System.out.println("E"+ w +  ": "  + Ew.p());
				EreignisMenge Sv = ENachricht(t -> t.get(0)==v);
				//System.out.println("S"+ v +  ": "  + Sv.p());
				//System.out.println("S" + v + "|E"  + w +  ": " + 
				//		Menge.BEDINGUNG(Sv, Ew).p());
			}
		}
		Linear l =  new Linear(4, 2);
		while(l.hasNext()){
			Tupel tupel = l.next();
			Ereignis e = ENachricht(t -> t.equalsNonSize(tupel));
			//System.out.println(tupel + ": " + e.p());

		}
	}
	public EDraw ENachricht(Function<Tupel,Boolean> f){
		return nachricht.draw(f);
	}
}
