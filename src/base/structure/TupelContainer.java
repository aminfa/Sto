package base.structure;

import java.util.ArrayList;
import java.util.Iterator;

public class TupelContainer implements Iterable<Tupel>{
	private TupelTree tt;
	public TupelContainer(){
		tt = new TupelTree(-1);
	}
	public void add(Tupel t){
		if(t==null)return;
		if(t.n == 0) return;
		tt.addDown(t);
	}
	 private void addDown(Tupel t, int index, TupelTree c){

		if(c.value==t.get(index)){
			c.addDown(t, index+1);
		}
		else {
			if(c.sibling==null){
				c.sibling = new TupelTree(t.get(index));
			}
			addDown(t,index,c.sibling);
		}
	}
	 public int count(){
		 return tt.countUp();
	 }
	class TupelTree{
		TupelTree(int v){
			value = v;
		}
		int counter = 0;
		int value;
		TupelTree child;
		TupelTree sibling;
		void addDown(Tupel t){
			addDown(t,0);
		}
		void addDown(Tupel t, int index){
			if(t.n == index){
				counter++;
				return;
			}
			else if(child== null){
				child = new TupelTree(t.get(index));
			}
			TupelContainer.this.addDown(t,index,child);
		}
		int countUp(){
			return counter +
					(child==null?0:child.countUp())+ 
					(sibling==null?0:sibling.countUp());
		}
		void addInto(ArrayList<Tupel> list){
			Tupel t = new Tupel(0);
			if(child!=null){
				child.addInto(t, list);
			}
		}
		void addInto(Tupel t, ArrayList<Tupel> list){
			if(counter!=0){
				list.add(t.addUp(value));
			}
			if(child!=null){
				child.addInto(t.addUp(value), list);
			}
			if(sibling != null){
				sibling.addInto(t, list);
			}
		}
	}
	@Override
	public Iterator<Tupel> iterator() {
		return toList().iterator();
	}
	public ArrayList<Tupel> toList(){
		ArrayList<Tupel> arr =  new ArrayList<Tupel>();
		tt.addInto(arr);
		return arr;
	}
}
