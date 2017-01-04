package base.verteilung;

import base.structure.Tupel;

public class Laplace implements Verteilung {
	private double n;
	public Laplace(int n){
		setN(n);
	}
	/*@Override
	public Double apply(Ereignis e) {
		GrundMenge gm = e.gM();
		double  counter  = 0.;
		for(int  t : gm){
			
			if(e.isIn(Tupel.create(t)))
				counter++;
		}
		double p = counter / (double) gm.Macht;
		return p;
	}*/
	public void setN(int n){
		this.n =n;
	}
	@Override
	public double p(Tupel t) {
		double p = 1;
		for(int i : t){
			p *= p(i);
		}
		return p;
	}
	public double p(int i) {
		return 1 / n;
	}
}
