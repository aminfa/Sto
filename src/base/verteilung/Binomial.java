package base.verteilung;

import java.math.BigDecimal;

import base.structure.Tupel;

public class Binomial implements Verteilung{
	
	int n;
	double p;
	BigDecimal coeff;
	

	double cache[];
	
	Binomial(int n, double p) {
		n--;
		this.n = n;
		this.p = p;
		
		this.cache = new double[n+1];
		for (int i = 0; i < cache.length; i++) {
			cache[i] = -1.;
		}
	}
	
	public static Binomial coinToss(int n) {
		Binomial b = new Binomial(n, 1./2.);
		b.coeff =  BigDecimal.valueOf(2).pow(n-1); 
		return b;
	}

	@Override
	public double p(Tupel t) {
		double p = 1;
		for(int i : t){
			p *= p(i);
		}
		return p;
	}
	

	@Override
	public double p(int i) {
		if(cache[i] == -1.) {
			cache[i] = calcP(i).doubleValue();
		}
		return cache[i];
	}
	
	
	private BigDecimal calcP(int k) {
		if(k > n || k < 0) {
			throw new ArithmeticException("K should be within [0." + n + "], but is: " + k);
		}
		BigDecimal coeff;
		if(this.coeff != null) {
			coeff = this.coeff;
		}
		else {
			BigDecimal p = BigDecimal.ONE.divide(BigDecimal.valueOf(this.p)).pow(k);
			BigDecimal q = BigDecimal.ONE.divide(BigDecimal.valueOf(1-this.p)).pow(n-k);
			coeff = p.multiply(q);
		}
		return binomial(n, k).divide(coeff);
	}
	
	static BigDecimal binomial(final int N, final int K) {
		BigDecimal ret = BigDecimal.ONE;
	    for (int k = 0; k < K; k++) {
	        ret = ret.multiply(BigDecimal.valueOf(N-k))
	                 .divide(BigDecimal.valueOf(k+1));
	    }
	    return ret;
	}


}
