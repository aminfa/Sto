package bs;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import base.menge.e.*;
import base.p.Wahr;
import base.structure.KZMode;
import base.structure.Tupel;
import base.verteilung.Laplace;

@SuppressWarnings("unused")
public class Skat_Test {
	@Test
	public void test(){
		StichProbe deck = new StichProbe(8,8,false,new Laplace(8));
		Function<Tupel,Boolean> fn  = new Function<Tupel, Boolean>(){

			@Override
			public Boolean apply(Tupel t) {
				
					int bubS1 = 0;
					for(int i = 0;i <t.n;i++){
						if(t.get(i) <= 3)
							bubS1++;
						if(bubS1 == 4) return true;
						if(i - bubS1 >3) return false;
					}
					return true;
			
			}
			
		};
		Function<Tupel,Boolean> xIn  = new Function<Tupel, Boolean>(){

			@Override
			public Boolean apply(Tupel t) {
				int bubS1 = 0;
				for(int i = 0;i <t.n;i++){
					if(t.get(i) <= 3)
						bubS1++;
					if(bubS1 == 4) return true;
				}
				return false;
			
			}
			
		};
		EreignisMenge S1AlleBuben = deck.draw(fn);
		//Assertion.Assert(Wahr.P_EFF(S1AlleBuben, new KZMode(), xIn),1.);
		Assertion.Assert(Wahr.P_LAP(S1AlleBuben),1.);
	}
}
