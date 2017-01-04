package base.menge.e;

import java.util.Iterator;
import java.util.function.Function;

import base.menge.GrundMenge;
import base.structure.Baum;
import base.structure.DrawMode;
import base.structure.Tupel;
import base.verteilung.Verteilung;

public class WRaum extends GrundMenge{
	private int M, T;
	private DrawMode dm;
	private Function<Tupel, Boolean> in;
	private Verteilung v;
	/**
	 * 
	 * @param M	omega = { 0, ... , M }
	 * @param T times
	 * @param dm indicates whats in it or not.
	 */
	public WRaum(int M, int T, DrawMode dm, Function<Tupel, Boolean> in, Verteilung v){
		this.M = M;
		this.T = T;
		this.dm = dm;
		this.in = in;
		this.v = v;
	}
	
	@Override
	public Iterator<Tupel> iterator() {
		return new Baum(M, T, in, dm);
	}

	@Override
	public double p(int i) {
		return v.p(i);
	}

	@Override
	public double p(Tupel t) {
		return v.p(t);
	}

	@Override
	public double size() {
		double count = 0;
		for(Tupel t : this){
			count++;
		}return count;
		
	}

	@Override
	public int M() {
		return M;
	}

	@Override
	public int T() {
		return T;
	}

}
