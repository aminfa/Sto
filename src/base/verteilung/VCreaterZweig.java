package base.verteilung;

import base.structure.Tupel;

public class VCreaterZweig {
	private int n;
	private int t;
	Tree myTree;
	
	public VCreaterZweig(int n, int t){
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
		nod.zweig = p;
	}
	public Verteilung getV(){
		myTree.calcZweigChildren();
		myTree.calcP();
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
		private double p;
		double zweig;
		int height;
		Tree(Tree p,  int h){
			parent = p;
			this.zweig = -1.;
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
		void calcZweigChildren(){
			double zweigSum = 0.;
			int rest = 0;
			for(int i = 0; i<  n; i++){
				if(children[i].zweig != -1){
					zweigSum += children[i].zweig;
				}else rest++;
			}
			if(zweigSum > 1.001 || (zweigSum<0.99 && rest ==0 ))
				throw new ArithmeticException();
			double restP = (1. - zweigSum)
							/(double)rest;
			for(Tree t : children){
				if(t.zweig == -1.){
					t.zweig = restP;
				}
			}
			if(height>1)
			for(int i = 0; i<  n; i++){
					children[i].calcZweigChildren();
			}
			
		}
		void calcP(){
			for(Tree t : children){
				t.p = t.zweig * p;
				t.calcP();
			}
		}
		double p(Tupel t, int index){
			if(height==0) return p;
			if(t.n==index) return p;
			return  children[t.get(index)].p(t,index+1);
		}
	}
	
}
