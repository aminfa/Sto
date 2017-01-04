package bs;

import static org.junit.Assert.fail;

import org.junit.Test;

import base.menge.GrundMenge;
import base.menge.Menge;
import base.menge.e.EDraw;
import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.p.Wahr;
import base.verteilung.Laplace;

public class FunfKampf_Test {

	@Test
	public void test() {
		int n = 6;
		StichProbe los = new StichProbe(n,n,false,new Laplace(n));
		EreignisMenge EinseigenesPferd = los.draw( t -> {
			return t.get(0) == 0;
		});

		EreignisMenge NeigenesPferd = getJreitetJ(n-1, los, n);
		
		if(Wahr.P_LAP(NeigenesPferd) != 1. / (double)n)fail();
		if(Wahr.P_LAP(EinseigenesPferd) != 1. / (double)n)fail();
		EreignisMenge verein = Menge.VEREIN(EinseigenesPferd, NeigenesPferd);
		if(Wahr.P_LAP(verein)!=0.3) fail(); // n = 6
		//if(verein.p()!=0.23214285714285635) // n =8
			//fail();
		
		EreignisMenge[] es = new EreignisMenge[n];
		for(int i = 0; i< n; i++){
			es[i] = getJreitetJ(i, los, n);
		}
		EreignisMenge all = es[0];
		for(int i = 1; i < n; i++){
			all = Menge.VEREIN(all, es[i]) ;
		}
		if(Wahr.P_LAP(all)!=0.6319444444444444)
			fail();
	}
	public static EreignisMenge getJreitetJ(int j, StichProbe los, int times){
		return los.draw( t -> {
			if(t.n < j+1){
				for(int i : t){
					if(i == j) return false;
				}
				return true;
			}
			return t.get(j) == j;
		});
	}
	
}
