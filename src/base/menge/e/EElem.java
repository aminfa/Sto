package base.menge.e;

import java.util.Iterator;

import base.menge.GrundMenge;
import base.structure.Tupel;

public class EElem extends EreignisMenge {

	private Tupel t;
	public EElem(GrundMenge gm, Tupel t) {
		super(gm ,1);
		this.t = t;
	}
	@Override
	public boolean elementOf(Tupel t) {
		return t.equalsNonSize(this.t);
	}
	@Override
	public Iterator<Tupel> iterator() {
		return new Iterator<Tupel>(){
			boolean first = true;
			@Override
			public boolean hasNext() {
				return first;
			}

			@Override
			public Tupel next() {
				if(first == false){
					return null;
				}
				first = false;
				return t;
			}
			
		};
		
	}
	@Override
	public double size() {
		return 1;
	}
	@Override
	public double p() {
		double p= 0.0;
		for(Tupel t : this){
			p += gm.p(t);
		}
		return p;
	}
	

}
