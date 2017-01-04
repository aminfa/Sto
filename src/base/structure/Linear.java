package base.structure;
import java.util.Iterator;

import base.menge.e.EreignisMenge;
public class Linear implements Iterator<Tupel> {
	private int counter[];
	private int max;
	private EreignisMenge em;
	public Linear(int max, int n_mal){
		counter = new int[n_mal];
		this.max = max;
		em = null;
	}
	public Linear(int max, int n_mal, EreignisMenge em){
		this(max,n_mal);
		this.em = em;
	}
	@Override
	public boolean hasNext() {
		for(int c : counter){
			if(c>=max){
				return false;
			}
		}
		return true;
	}

	@Override
	public Tupel next() {	
		Tupel t = Tupel.create(counter);
		goUp(0);
		if(em!=null && !em.isIn(t))
			t = next();
		return t;
	}
	private void goUp(int pos){
		if(counter[pos] == max-1){
			if(pos<counter.length-1){
				goUp(pos+1);
				counter[pos]=0;
			}
			else{
				counter[pos]++;
			}
		}
		else{
			counter[pos]++;
		}
	}
	
}
