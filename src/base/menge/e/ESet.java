package base.menge.e;

import java.util.Iterator;

import base.menge.GrundMenge;
import base.structure.Tupel;

public class ESet extends EreignisMenge {
	private Iterable<Tupel> set;
	private int sized = -1;
	public ESet(GrundMenge gm, int times, Iterable<Tupel> set) {
		super(gm, times);
		this.set = set;
	}
	@Override
	public double p() {
		double p = 0.;
		for(Tupel t: set){
			p+=gm.p(t);
		}
		return p;
	}
	@Override
	public Iterator<Tupel> iterator() {
		return set.iterator();
	}
	@Override
	protected boolean elementOf(Tupel t) {
		for(Tupel tupel : this){
			if(tupel.equals(t)){
				return true;
			}
		}
		return false;
	}
	@Override
	public double size() {
		if(sized != -1)
			return sized;
		sized++;
		for(@SuppressWarnings("unused") Tupel t : this){
			sized++;
		}
		return size();
	}
}
