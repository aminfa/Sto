package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import base.structure.Tupel;
import base.structure.TupelContainer;

public class TupelTest {

	@Test
	public void test() {
		Tupel t = new Tupel(4);
		t.add(10);
		t.add(12);
		t.add(3);
		t.add(4);
		if(t.get(0)!=10)fail();
		if(t.get(1)!=12)fail();
		if(t.get(2)!=3)fail();
		if(t.get(3)!=4)fail();
		Tupel t1 = new Tupel(3);
		t1.add(1,2,3,4,5);
		if(t1.get(0)!=1)fail();
		if(t1.get(1)!=2)fail();
		if(t1.get(2)!=3)fail();
		Tupel t2 = Tupel.create(61,32,12,6);
		if(t2.get(0)!=61)fail();
		if(t2.get(1)!=32)fail();
		if(t2.get(2)!=12)fail();
		if(t2.get(3)!=6)fail();
		Tupel t3 = Tupel.create();
		Tupel t4 = new Tupel(5);
		t4.add(t3);
		if(t4.get(0)!=0)fail();
		if(t4.get(1)!=0)fail();
		if(t4.get(2)!=0)fail();
		if(t4.get(3)!=0)fail();
		if(t4.get(4)!=0)fail();
		t4.add(t2);
		if(t4.get(0)!=61)fail();
		if(t4.get(1)!=32)fail();
		if(t4.get(2)!=12)fail();
		if(t4.get(3)!=6)fail();
		if(t4.get(4)!=0)fail();
		t4.add(t,1);
		if(t4.get(0)!=61)fail();
		if(t4.get(1)!=32)fail();
		if(t4.get(2)!=12)fail();
		if(t4.get(3)!=6)fail();
		if(t4.get(4)!=12)fail();
		Tupel[] ts = (Tupel[]) t4.divide(2,2,1);
		if(ts[0].get(0)!=61)fail();
		if(ts[0].get(1)!=32)fail();
		if(ts[1].get(0)!=12)fail();
		if(ts[1].get(1)!=6)fail();
		if(ts[2].get(0)!=12)fail();
		
		TupelContainer tc = new TupelContainer();
		tc.add(Tupel.create(0,0,0));
		tc.add(Tupel.create(0,0,0));
		tc.add(Tupel.create(0,0,0));
		tc.add(Tupel.create(0,0,1));
		tc.add(Tupel.create(0,1,0));
		tc.add(Tupel.create(0,1,0));
		tc.add(Tupel.create(1,0,0));
		tc.add(Tupel.create(1,0,0));
		tc.add(Tupel.create(2,0,0));
		if(tc.count() != 9) fail();
		if(tc.toList().size()!=5)fail();
	}

}
