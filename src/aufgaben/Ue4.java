package aufgaben;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import base.menge.e.*;
import base.structure.Tupel;
import base.verteilung.Laplace;
import base.zv.Px;

public class Ue4 {

	@Test
	public void test() {
		StichProbe trommel = new StichProbe(10,1,false,new Laplace(10));
		Function<Tupel,Double> fY = t -> {
			int max = -1;
			for(int i : t){
				if(i>max){
					max =  i;
				}
			}
			return (double)max+1;
		};
		Function<Tupel,Double> fX = t -> {
			double max = -1;
			for(int i : t){
				if(i>max){
					if((t.get(t.n-1)) == i){
						return 1.;
					}
					max =  i;
				}
			}
			return 0.;
		};
		Function<Tupel,Double> fZ = t -> {
			double anzahl = 0;
			double max = -1;
			for(int i : t){
				if(i>max){
					max =  i;
					anzahl++;
				}
			}
			return anzahl;
		};
		
		Px Yi = new Px(trommel, fY);
		
		double summeX = 0.;
		
		for(int index = 1; index < 11; index++){
			trommel.setTimes(index);
			summeX += Px.Erwartungswert(trommel,fX);
			System.out.println("Y" + index + ": " + Px.Erwartungswert(trommel,fY));
			System.out.println("Z" + index + ": " + Px.Erwartungswert(trommel,fZ));
			System.out.println("summe" + index + ": " + summeX);
			System.out.println("X" + index + ": " + Px.Erwartungswert(trommel,fX)+ "\n");
		}
		
		
	}

}
