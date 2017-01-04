package aufgaben;

import static org.junit.Assert.*;

import org.junit.Test;

import base.menge.e.StichProbe;
import base.structure.Tupel;
import base.verteilung.Verteilung;
import base.zv.Px;

public class Ue3 {

	@Test
	public void test() {
		int ai = 3;
		double p = 0.3;
		StichProbe Leitung =  new StichProbe(2, ai, true, new Verteilung() {
			
			@Override
			public double p(int i) {
				if(i == 0)
					return p;
				else return 1 - p;
			}
			
			@Override
			public double p(Tupel t) {
				double p0 = 1;
				for(int i : t){
					p0 *= p(i);
				}
				return p0;
			}
		});
		Px verbindung1 = new Px(Leitung, t->{
			for(int i : t){
				if(i == 0){
					return 0.;
				}
			}
			return 1.;
		});
		Px verbindung2 = new Px(Leitung, t->{
			for(int i : t){
				if(i == 1){
					return 1.;
				}
			}
			return 0.;
		});
		Px verbindung3 = new Px(Leitung, t->{
			if(t.n < 3) return 0.;
			if(t.get(0)==0){
				return 0.;
			}
			if(t.get(1)==1){
				return 1.;
			}
			if(t.get(2)==1){
				return 1.;
			}
			return 0.;
		});
		System.out.println(verbindung1.dichte(1.));
		System.out.println(verbindung2.dichte(1.));
		System.out.println(verbindung3.dichte(1.));
		System.out.println(Px.Varianz(verbindung1));
		System.out.println(Px.Varianz(verbindung2));
		System.out.println(Px.Varianz(verbindung3));
	}

}
