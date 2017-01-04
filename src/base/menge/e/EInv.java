package base.menge.e;

import java.util.Iterator;

import base.structure.Tupel;

public class EInv extends EreignisMenge {
	private EreignisMenge s;
	public EInv(EreignisMenge source) {
		super(source.gm, source.times());
		s = source;
	}

	@Override
	public double p() {
		return 1. - s.p();
	}

	@Override
	protected boolean elementOf(Tupel t) {
		return !s.isIn(t);
	}

	@Override
	public double size() {
		return gm.size()-s.size();
	}

	@Override
	public Iterator<Tupel> iterator() {
		return new InvIterator(s.gm.iterator());
	}
	class InvIterator implements Iterator<Tupel>{
		Iterator<Tupel> base;
		Tupel next;
		InvIterator(Iterator<Tupel>b){
			base = b;
			next = next();
			
		}
		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public Tupel next() {
			Tupel n = base.next();
			while(s.isIn(n)){
				if(base.hasNext())
					n = base.next();
				else{
					return null;
				}
			}
			Tupel back = next;
			next = n;
			return back;
		}
		
	}
}
