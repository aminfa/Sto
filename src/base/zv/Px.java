package base.zv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import base.menge.GrundMenge;
import base.menge.e.ESet;
import base.menge.e.StichProbe;
import base.structure.Tupel;
import base.structure.TupelContainer;

public class Px implements Iterable<Double>{
	private GrundMenge O;
	private Function<Tupel, Double> x;
	private ArrayList<Double> Wx;
	private ArrayList<TupelContainer> tcs;
	private ArrayList<ESet> sets;
	private Function<Double, Double> dichte;
	
	public Px(GrundMenge gm, Function<Tupel, Double> x){
		O =gm;
		this.x=x;
		dichte = calcDichte();
	}
	
	public static Px bernoulli(GrundMenge gm, Tupel trefferTupel) {
		return new Px(gm, t -> {
			for(int i : trefferTupel) {
				if(i == t.get(0)) {
					return 1.;
				}
			}
			return 0.;
		});
	}

	public ESet p(Function<Double, Boolean> fn){
		TupelContainer tc = new TupelContainer();
		for(Tupel tupel : O){
			if(fn.apply(x.apply(tupel))){
				tc.add(tupel);
			}
		}
		ESet set = new ESet(O, O.T(), tc.toList());
		return set;
	}
	public ESet p( Double d){
		return p( x -> {
			return x == d;
		});
	} 
	public void reset(){
		dichte = calcDichte();
	}
	public static double Erwartungswert(Px X){
		double E = 0.;
		for(double x : X){
			E += x * X.dichte(x);
		}
		return E;
	}
	public static double Varianz(Px X){
		double Ex = 0.;
		double Ex2 = 0.;
		for(double x : X){
			Ex += x * X.dichte(x);
			Ex2 += x * x * X.dichte(x);
		}
		return Ex2 - (Ex *Ex);
	}
	public static double Erwartungswert(StichProbe gm, Function<Tupel, Double> X){
		double E = 0.;
		for(Tupel w: gm){
			E += X.apply(w) * gm.p(w);
		}
		return E;
	}
	public static double Varianz(StichProbe gm, Function<Tupel, Double> X){
		double Ex = Erwartungswert(gm,X);
		double Ex2 = Erwartungswert(gm,(t)->X.apply(t)* X.apply(t));
		return Ex2 - (Ex * Ex);
	}
	public double dichte(Double x){
		return dichte.apply(x);
	}
	private Function<Double,Double> calcDichte(){
		Wx = new ArrayList<Double>();
		tcs = new ArrayList<TupelContainer>();
		
		for(Tupel t : O){
			double x = this.x.apply(t);
			if(!Wx.contains(x)){
				Wx.add(x);
				tcs.add( new TupelContainer());
			}
			tcs.get(Wx.indexOf(x)).add(t);
		}
		sets = new ArrayList<ESet>();
		for(TupelContainer tc: tcs){
			sets.add(new ESet(O, O.T(), tc.toList()));
		}
		return new Function<Double,Double>(){

			@Override
			public Double apply(Double x) {
				if(Wx.contains(x)){
					return sets.get(Wx.indexOf(x)).p();
				}
				return 0.;
			}
		};
	}

	@Override
	public Iterator<Double> iterator() {
		return Wx.iterator();
	}
}
