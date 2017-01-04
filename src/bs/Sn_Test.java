package bs;
import static bs.Assertion.Assert;
import static org.junit.Assert.*;

import org.junit.Test;

import base.menge.Menge;
import base.menge.e.EInv;
import base.menge.e.EDraw;
import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.p.Wahr;
import base.verteilung.Laplace;

public class Sn_Test {

	@Test
	public void test() {
		int n = 5;
		StichProbe sn = new StichProbe(n,n, false, new Laplace(n));
		EreignisMenge e0 = sn.draw( t -> {
			return t.get(0) == 0;
		});
		EreignisMenge e1 = sn.draw( t -> {
			if(t.get(0) == 1) return false;
			if(t.n <2 ) return true;
			return t.get(1) == 1;
		});
		double N = n;
		Assert(Wahr.P_LAP(e0),1/N);
		//System.out.println(Wahr.P_LAP(e0));
		Assert(Wahr.P_LAP(e1),1/N);
		//System.out.println(e1.p());
		EreignisMenge e1c = new EInv(e1);
		Assert(Wahr.P_LAP(Menge.SCHNITT(e0, e1)),1/(N* ( N -1)));
		//System.out.println(Wahr.P_LAP(Menge.SCHNITT(e0, e1)));
		Assert(Wahr.P_LAP(Menge.SCHNITT(e0, e1c)),(N-2)/(N*(N-1)));
		//System.out.println(Menge.SCHNITT(e0, e1c).p());
		Assert(Wahr.P_LAP(Menge.BEDINGUNG(e0, e1)),1/(N-1));
		//System.out.println(Wahr.P_LAP(Menge.BEDINGUNG(e0, e1)));
		Assert(Wahr.P_LAP(Menge.BEDINGUNG(e0, e1c)),(N-2)/((N-1)*(N-1)));
		//System.out.println(Wahr.P_LAP(Menge.BEDINGUNG(e0, e1c)));
	}

}
