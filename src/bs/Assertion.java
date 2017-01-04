package bs;

import static org.junit.Assert.fail;
public class Assertion {
	public static void Assert(double a, double b){
		if(Math.abs(a-b) > 0.001)
			fail(a + " != " + b);
	}
}
