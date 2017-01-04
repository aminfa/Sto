package tests;
import static bs.Assertion.Assert;
import static org.junit.Assert.*;

import org.junit.Test;

import base.menge.Serie;
import base.menge.e.StichProbe;
import base.structure.TTupel;
import base.structure.Tupel;
import base.verteilung.Laplace;

public class SerieTest {

	@Test
	public void test() {
		StichProbe s1 = new StichProbe(2,2, true, new Laplace(3));
		StichProbe s2 = new StichProbe(3,3, false, new Laplace(3));
		StichProbe s3 = new StichProbe(3,1, true, new Laplace(3));
		Serie s = new Serie(s1,s2,s3);
		Assert(s.size(), s1.size() * s2.size() * s3.size());
		Assert(s.p(TTupel.create(
				Tupel.create(0,0),
				Tupel.create(0,1,2),
				Tupel.create(2))),0.006172839506172836);
	}

}
