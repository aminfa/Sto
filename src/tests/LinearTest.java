package tests;

import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import base.menge.e.StichProbe;
import base.structure.Linear;
import base.structure.Tupel;
import base.verteilung.Laplace;

public class LinearTest {

	@Test
	public void test() {
		int n=4;
		int t = 10;
		StichProbe gm = new StichProbe(n,t,true, new Laplace(n));
		int i = 0;
		Iterator<Tupel> it = new Linear(n,t);
		Tupel first = it.next();
		if(!first.equals(Tupel.create(0,0,0,0,0,0,0,0,0,0)))
			fail();
		while(it.hasNext()){
			Tupel last = it.next();
			if(!it.hasNext()){
				if(!last.equals(Tupel.create(n-1,n-1,n-1,n-1,n-1,n-1,n-1,n-1,n-1,n-1)))
					fail();
				
			}
			//System.out.println(last);
			i++;
		}
		if(i+1 != Math.pow(n,t)){
			fail();
		}
			
	}
}
