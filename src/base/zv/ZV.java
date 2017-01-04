package base.zv;

import java.math.BigInteger;

public abstract class ZV {
	public abstract boolean werteMenge(double d);
	public abstract double p(double d);
	public abstract double E();
	public abstract double Var();
	public static ZV BINOMIAL(final int N, double p){
		if(p>1.)p=1.;
		else if(p<0.)p=0;
		double tp = p;
		return new ZV(){

			@Override
			public boolean werteMenge(double d) {
				if(d-(double)(int)d!=0.)
					return false;
				if(d<0) return false;
				else if(d>N) return false;
				else return true;
			}

			@Override
			public double p(double d) {
				if(!werteMenge(d))
					return 0.;
				return (double)binomial(N,(int)d) * Math.pow(tp, d) * Math.pow(1-tp, N-d);
			}

			@Override
			public double E() {
				return (double)N * tp;
			}

			@Override
			public double Var() {
				return (double)N * tp*(1-tp);
			}
		};
	}
	public static ZV BERNOULLI(double p){
		if(p>1.)p=1.;
		else if(p<0.)p=0;
		double pTemp = p;
		return new ZV(){

			@Override
			public boolean werteMenge(double d) {
				if(d==0.||d==1.) 
					return true;
				return false;
			}

			@Override
			public double p(double d) {
				if(d==1.) return pTemp;
				if(d==0.) return 1. -pTemp;
				else return 0.;
			}

			@Override
			public double E() {
				return pTemp;
			}

			@Override
			public double Var() {
				return pTemp * (1. - pTemp);
			}
		};
	}
	public static double  binomial(final int N, final int K) {
		double ret = 1;
	    for (int k = 0; k < K; k++) {
	        ret = (ret * (N-k)) /k+1;
	    }
	    return ret;
	}
}
