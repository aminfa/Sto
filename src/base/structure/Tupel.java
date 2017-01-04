package base.structure;

import java.util.Iterator;

public class Tupel implements Iterable<Integer> {
	public final int n;
	public final int[] arr;
	private int counter = 0;
	public Tupel(int n){
		this.n = n;
		arr= new int[n];
	}
	public static Tupel create(int...is){
		Tupel t = new Tupel(is.length);
		t.add(is);
		return t;
	}
	public void add(int value){
		if(counter >= n)return;
		else arr[counter]=value;
		counter++;
	}
	public void add(int...is){
		for(int i = 0; i< is.length;i++){
			if(counter >= n)return;
			else arr[counter]=is[i];
			counter++;
		}
	}
	public void add(Tupel t){
		for(int i = 0; i< t.n;i++){
			if(counter >= n)return;
			else arr[counter]=t.get(i);
			counter++;
		}
	}
	public void add(Tupel t, int offset){
		for(int i = offset; i< t.n;i++){
			if(counter >= n)return;
			else arr[counter]=t.get(i);
			counter++;
		}
	}
	@SuppressWarnings("unused")
	private Tupel addUp(){
		Tupel t = new Tupel(n+1);
		t.add(this);
		return t;
	}
	public Tupel addUp(int v){
		Tupel t = new Tupel(n+1);
		t.add(this);
		t.add(v);
		return t;
	}
	@SuppressWarnings("unused")
	private Tupel shrink(){
		Tupel t = new Tupel(n-1);
		t.add(this);
		return t;
	}
	public int get(int i){
		if(i>=n||i<0)return 0;
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
	public Tupel[] divide(int...is ){
		Tupel[] ts = new Tupel[is.length];
		int runner = 0;
		for(int i = 0 ; i < is.length;i ++){
			ts[i] = new Tupel(is[i]);
			ts[i].add(this, runner);
			runner = is[i];
		}
		return ts;
	}
	public boolean equals(Tupel t){
		if(t==null)return false;
		if(t.n!=n)return false;
		for(int i =0; i< n&& i<t.n;i++){
			if(get(i)!=t.get(i))
				return false;
		}
		return true;
	}
	public boolean equalsNonSize(Tupel t){
		if(t==null)return false;
		for(int i =0; i< n&& i<t.n;i++){
			if(get(i)!=t.get(i))
				return false;
		}
		return true;
	}
	@Override
	public Iterator<Integer> iterator() {
		return new TupelIt();
	}
	class TupelIt implements Iterator<Integer>{
		int counter = 0;
		@Override
		public boolean hasNext() {
			return counter < n;
		}

		@Override
		public Integer next() {
			return get(counter++);
			
		}
		
	}
	public int last(){
		return arr[n-1];
	}
	public Tupel clone(){
		Tupel t = new Tupel(n);
		t.add(this);
		return t;
	}
	public void set(int pos, int value){
		if(pos<0 || pos >= n )
			return;
		else 
			arr[pos]=value;
	}
}
