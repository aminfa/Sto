package tests;

import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import base.menge.e.StichProbe;
import base.structure.Faktoriel;
import base.structure.Tupel;
import base.verteilung.Laplace;

public class FaktorielTest {

	@Test
	public void test() {
		int n=6;
		int t = 4;
		StichProbe gm = new StichProbe(n,  t,false, new Laplace(n));
		int i = 0;
		Iterator<Tupel> it = new Faktoriel(n,t);
		Tupel first = it.next();
		if(!first.equals(Tupel.create(0,1,2,3)))
			fail();
		while(it.hasNext()){
			Tupel last = it.next();
			if(!it.hasNext()){
				if(!last.equals(Tupel.create(n-1,n-2,n-3,n-4)))
					fail();
			}
			i++;
		}
		if(i+1 != gm.size()){
			fail();
		}
			
	}
}
