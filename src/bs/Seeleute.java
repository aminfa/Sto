package bs;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import base.menge.e.*;
import base.p.Wahr;
import base.structure.KZMode;
import base.structure.Tupel;
import base.verteilung.Laplace;
import base.zv.Px;

public class Seeleute {
	@Test
	public void test(){
		int n = 7;
		StichProbe seeleute = new StichProbe(n, n, false, new Laplace(n));
		Px X = new Px(seeleute, t->{
			double count = 0;
			for(int i = 0; i < t.n; i++){
				if(t.get(i) == i)
					count++;
			}
			return count;
		});
		if(1.-Px.Erwartungswert(X)>0.0001){
			fail(""+Px.Erwartungswert(X));
		}
		if(1.-Px.Varianz(X)>0.0001){
			fail(""+Px.Erwartungswert(X));
		}
	}
}
