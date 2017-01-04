package base.verteilung;

import base.structure.Tupel;

public class Verteilung_KZ implements Verteilung {
	private Verteilung baseV;
	private double factor= 1.;
	public Verteilung_KZ(Verteilung v){
		baseV =v;
	}
	private void draw(int i){
		if(factor == 0.) return;
		factor = 1./((1./factor)-baseV.p(Tupel.create(i)));
		if(factor < 0.)
			factor = 0.;
	}
	private void reset(){
		factor = 1.;
	}
	public double p(Tupel t){
		double p =1;
		reset();
		for(int i : t){
			p *= baseV.p(i)*factor;
			draw(i);
		}
		return p;
	}
	//@Override
	public double p(int i) {
		// TODO -- change Tupel.create(i) to i
		return baseV.p(i)*factor;
	}

}
