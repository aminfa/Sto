package base.verteilung;

import base.structure.Tupel;

public class VCreater {
	private int n;
	private int t;
	boolean done = false;
	Tree myTree;
	public VCreater(int n, int t){
		this.n = n;
		this.t = t;
		myTree = new Tree(null,t);
		myTree.p = 1;
	}
	public void addTupel(Tupel t, double p){
		if(t.n>this.t)return ;
		Tree nod = myTree;
		for(int i : t){
			nod = nod.children[i];
		}
		nod.p = p;
		nod.parent.addUpChilds();
	}
	public Verteilung done(){
		done = true;
		myTree.calcChildren();
		return new Verteilung(){

			@Override
			public double p(Tupel t) {
				return myTree.p(t, 0);
			}

			@Override
			public double p(int i) {
				return myTree.children[i].p;
			}
			
		};
	}
	class Tree{
		Tree[] children;
		Tree parent;
		double p;
		int height;
		Tree(Tree p,  int h){
			parent = p;
			this.p = -1.;
			height = h;
			if(h == 0){
				children = new Tree[0];
			}
			else{
				children = new Tree[n];
				for(int i = 0 ;i < n;i++){
					children[i] = new Tree(this,h-1 );
				}
			}
		}
		void calcChildren(){
			double zweigP = 0.;
			int rest = 0;
			for(int i = 0; i<  n; i++){
				if(children[i].p != -1){
					zweigP += p*children[i].p;
				}else rest++;
			}
			if(zweigP > 1.001)
				throw new ArithmeticException();
			double restP = (1. - zweigP)/(double)rest;
			for(int i = 0; i<  n; i++){
				if(height>0)
					children[i].calcChildren();
			}
		}
		void addUpChilds(){
			if(height == 0 ){
				if(parent!=null)
					parent.addUpChilds();
				return;
			}
			double tempP = 0;
			for(int i = 0; i<  n; i++){
				if(children[i].p!=-1)
					tempP += children[i].p;
			}
			if(tempP > 1.)
				throw new ArithmeticException();
			if(p < tempP)
				p = tempP;
			if(parent!=null)
				parent.addUpChilds();
		}
		double p(Tupel t, int index){
			if(!done) return 0;
			if(height==0) return p;
			if(t.n-1==index) return p;
			return  children[t.get(index)].p(t,index-1);
		}
	}
	
}
