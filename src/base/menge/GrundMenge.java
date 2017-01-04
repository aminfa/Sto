package base.menge;

import java.util.Iterator;

import base.structure.Linear;
import base.structure.Tupel;

public abstract class GrundMenge implements Iterable<Tupel>  {
	public abstract double p(int i);
	public abstract double p(Tupel t);
	//public abstract void reset();
	public abstract double size();
	public abstract int M();
	public abstract int T();
	//public abstract Iterable<Tupel> powerT(int t);
	
}
