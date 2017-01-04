package base.structure;

import java.util.Iterator;
import java.util.function.Function;

import base.menge.e.EreignisMenge;

public class Baum implements Iterator<Tupel> {
	private int t;
	private int n;
	private Function<Tupel, Boolean> E;
	private Nod cNod;
	//private boolean zuruck;
	private DrawMode im;
	//For efficient 
	private boolean efficient = false;
	private Function<Tupel,Boolean>xIn;
	public Baum(int max, int n_mal, Function<Tupel, Boolean> baumHandler, DrawMode im){
		t = n_mal;
		this.n = max;
		E = baumHandler;
		this.im = im;
		cNod =  new Nod(null,-1);
		cNod = cNod.next();
	}
	public Baum(int max, int n_mal, Function<Tupel, Boolean> baumHandler, DrawMode im, Function<Tupel,Boolean>xIn){
		t = n_mal;
		this.n = max;
		E = baumHandler;
		this.im = im;
		this.xIn = xIn;
		efficient = true;
		cNod =  new Nod(null,-1);
		cNod = cNod.next();
	}
	@Override
	public boolean hasNext() {
		return cNod!=null;
	}

	@Override
	public Tupel next() {
		Tupel t = cNod.toTupel();
		cNod = cNod.parent.next();
		return t;
	}
	public boolean isIn(Tupel t){
		return E.apply(t);
	}
	
	class Nod{
		int value;
		Nod parent;
		int counter = -1;
		Nod(Nod p,int v){
			parent = p;
			value =v;
			nextCounter();
		}
		public Tupel toTupel(){
			if(value==-1){
				return new Tupel(0);
			}
			int d = deep();
			Tupel t = new Tupel(d);
			addUp(t);
			return t;
			//return (Tupel) parent.toTupel().addUp(value);
		}
		private void addUp(Tupel t){
			if(value == -1)
			{
				return;
			}
			parent.addUp(t);
			t.add(value);
		}
		private void nextCounter(){
			if(counter > n) return ;
				counter++;

			if(parent != null){
				Tupel t = toTupel();
				if(!im.nextInLine(t, counter)) nextCounter();
			}
		}
		public int deep(){
			if(value==-1)return 0;
			else return 1+parent.deep();
		}
		public Nod next(){
			if(counter < n && deep()!=t){
				Nod n = new Nod(this, counter);
				nextCounter();
				Tupel tupel = n.toTupel();
				if(isIn(tupel)){
					if(tupel.n == t){
						return n;
					}
					// efficient 
					else if(efficient){
						if(xIn.apply(tupel)){
							return n;
						}
					}
					return n.next();
				}
				else return next();
			}
			if(parent==null){
				return null;
			}
			return parent.next();
		}
		public String toString(){
			String s="";
			s += "v:"+value+" ";
			s += "c:"+counter+" | ";
			if(parent!=null)
				s+= parent.toString();
			return s;
		}
	}
}
