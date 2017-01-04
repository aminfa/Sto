package base.structure;

import java.util.Iterator;

import base.menge.Concat;

public class Vektor implements Iterator<Integer> {
	private Concat gm;
	private Iterator itA;
	private Iterator itB;
	private T value;
	private int counter = 0;
	public Vektor(Concat gm){
		this.gm = gm;
		itA = gm.a().iterator();
		itB = gm.b().iterator();
		value = itA.next();
	}
	@Override
	public boolean hasNext() {
		return counter <gm.Macht;
	}

	@Override
	public Tupel<T> next() {
		Tupel<T> v = new Tupel<T>(2);
		v.add(value);
		boolean hasnext = itB.hasNext();
		v.add(itB.next());

		if(!hasnext){
			value = itA.next();
			itB = gm.b().iterator();
		}
		counter++;
		return v;
		
			
	}

}
