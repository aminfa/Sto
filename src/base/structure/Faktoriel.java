package base.structure;

import java.util.Iterator;

public class Faktoriel implements Iterator<Tupel>  {
	private int counter[];
	private int max;
	public Faktoriel(int max, int n_mal){
		counter = new int[n_mal];
		this.max = max;
		for(int i =0; i< n_mal;i++){
			counter[i] =i;
		}
		
		
	}
	public void goUP(int pos){
		if(pos>= counter.length)return;
		if(pos<0)return;
		int free=-1;
		for(int j = counter[pos]+1; j< max;j++){
			free = j;
			for(int i = 0; i< pos;i++){
				if(counter[i]==j){
					free = -1;
					break;
				}
			}
			if(free != -1){
				break;
			}
		}
		if(free==-1){
			counter[pos]  = -1;
			goUP(pos-1);
		}
		else{
			counter[pos++] = free;
			goUP(pos);
		}
	}
	@Override
	public boolean hasNext() {
		return counter[0]!=-1;
		//return loop < maxloop;
	}
	@Override
	public Tupel next() {
		Tupel t = Tupel.create(counter);
		goUP(counter.length-1);
		return t;
	}
}
