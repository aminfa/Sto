package aufgaben;

import static org.junit.Assert.*;

import org.junit.Test;

import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.p.Wahr;
import base.verteilung.Laplace;

public class Ue1Auf3 {

	@Test
	public void test() {
		StichProbe sp = new StichProbe(100,1,true, new Laplace(100));
		EreignisMenge e2o5 = sp.draw(t ->{
			return t.get(0)%2 == 0 || t.get(0)%5 == 0;
		});
		System.out.println(Wahr.P_LAP_print(e2o5));

		EreignisMenge e4o8o10 = sp.draw(t ->{
			return t.get(0)%4 == 0 || t.get(0)%8 == 0|| t.get(0)%10 == 0;
		});
		System.out.println(Wahr.P_LAP_print(e4o8o10));
	}

}
