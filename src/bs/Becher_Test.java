package bs;

import static bs.Assertion.Assert;

import org.junit.Test;

import base.menge.Menge;
import base.menge.e.EDraw;
import base.menge.e.EInv;
import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.p.Wahr;
import base.verteilung.Laplace;

public class Becher_Test {

	@Test
	public void test() {
		StichProbe dreiBecher = new StichProbe(3,1, false, new Laplace(3));
		EreignisMenge unter0 = dreiBecher.draw( t ->{
			return t.get(0)==0;
		});
		EreignisMenge unter1 = dreiBecher.draw( t ->{
			return t.get(0)==1;
		});
		EreignisMenge unter2 = dreiBecher.draw( t ->{
			return t.get(0)==2;
		});
		Assert(unter0.p(), 1./3.);
		Assert(Menge.BEDINGUNG(unter0, unter0).p(),1.)
			;
		Assert(Menge.BEDINGUNG(unter0, new EInv(unter0) ).p() , 0.)
			;
		Assert(Wahr.P_LAP(Menge.BEDINGUNG(unter1, new EInv(unter0) )) , 0.5)
			;
		Assert(Menge.BEDINGUNG(
				Menge.VEREIN(unter0, unter1), 
				new EInv(unter2)).p() , 1.);
	}

}
