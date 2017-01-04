package base.verteilung;

import base.structure.Tupel;

public interface Verteilung  {
	public double p(Tupel t);
	public double p(int i);
	/*
	public default double sum(int length){
		if(length == 0) return 0.;
		return p(length-1) + sum(length-1);
	}*/
}
