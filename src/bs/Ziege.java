package bs;

import static org.junit.Assert.*;

import org.junit.Test;

import base.menge.Menge;
import base.menge.e.EInv;
import base.menge.e.EDraw;
import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.structure.Tupel;
import base.verteilung.Laplace;

public class Ziege {

	@Test
	public void test() {
		Tupel tupel = Tupel.create(0);
		StichProbe sp = new StichProbe(3, 2,true, new Laplace(3));
		EreignisMenge e1 = sp.draw(t -> {
			return t.equalsNonSize(tupel);
		});
		EreignisMenge e2 = sp.draw( t -> {
			if(t.n < 2 ) return true;
			return t.get(1) == 2;
		});
		EreignisMenge e3 = sp.draw( t -> {
			if(t.n < 2 ) return true;
			return t.get(1) == 1;
		});
		e2 = new EInv(e2);
		Assertion.Assert(e2.p(),2./3.);
		//System.out.println(e2.p());
		Assertion.Assert(e1.p(),1./3.);
		//System.out.println(e1.p());
		Assertion.Assert(Menge.BEDINGUNG(e3,e2).p(),1./2.);
		//System.out.println(Menge.BEDINGUNG(e3,e2).p());
	}

}
