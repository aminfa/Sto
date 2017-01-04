package base.structure;

import java.util.Iterator;

public class TTupel implements Iterable<Tupel> {

	public final int n;
	public final Tupel[] arr;
	private int counter = 0;
	
	public TTupel(int n){
		this.n = n;
		arr= new Tupel[n];
	}
	public static TTupel create(Tupel...is){
		TTupel t = new TTupel(is.length);
		t.add(is);
		return t;
	}
	public void add(Tupel value){
		if(counter >= n)return;
		else arr[counter]=value;
		counter++;
	}
	public void add(Tupel...is){
		for(int i = 0; i< is.length;i++){
			if(counter >= n)return;
			else arr[counter]=is[i];
			counter++;
		}
	}
	public void add(TTupel t){
		for(int i = 0; i< t.n;i++){
			if(counter >= n)return;
			else arr[counter]=t.get(i);
			counter++;
		}
	}
	public void add(TTupel t, int offset){
		for(int i = offset; i< t.n;i++){
			if(counter >= n)return;
			else arr[counter]=t.get(i);
			counter++;
		}
	}
	public TTupel addUp(){
		TTupel t = new TTupel(n+1);
		t.add(this);
		return t;
	}
	public TTupel addUp(Tupel v){
		TTupel t =  addUp();
		t.add(v);
		return t;
	}
	public TTupel shrink(){
		TTupel t = new TTupel(n-1);
		t.add(this);
		return t;
	}
	public Tupel get(int i){
		if(i>=n||i<0)return null;
		else return arr[i];
	}
	public String toString(){
		String s = "[";
		for(int i = 0 ; i< n ;i ++){
				s+=arr[i];
				if(i != n-1){
					s+=", ";
				
			}
		}
		s+="]";
		return s;
	}
	public TTupel[] divide(int...is ){
		TTupel[] ts = new TTupel[is.length];
		int runner = 0;
		for(int i = 0 ; i < is.length;i ++){
			ts[i] = new TTupel(is[i]);
			ts[i].add(this, runner);
			runner = is[i];
		}
		return ts;
	}
	public boolean equals(TTupel t){
		if(t==null)return false;
		if(t.n!=n)return false;
		for(int i =0; i< n&& i<t.n;i++){
			if(get(i).equals(t.get(i)))
				return false;
		}
		return true;
	}
	public boolean equalsNonSize(TTupel t){
		if(t==null)return false;
		for(int i =0; i< n&& i<t.n;i++){
			if(get(i).equals(t.get(i)))
				return false;
		}
		return true;
	}
	@Override
	public Iterator<Tupel> iterator() {
		return new TupelIt();
	}
	class TupelIt implements Iterator<Tupel>{
		int counter = 0;
		@Override
		public boolean hasNext() {
			return counter < n;
		}

		@Override
		public Tupel next() {
			return get(counter++);
			
		}
		
	}
	public Tupel last(){
		return arr[n-1];
	}
	public TTupel clone(){
		TTupel t = new TTupel(n);
		t.add(this);
		return t;
	}
	public void set(int pos, Tupel value){
		if(pos<0 || pos >= n )
			return;
		else 
			arr[pos]=value;
	}
}
