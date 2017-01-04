package bs;

import static org.junit.Assert.*;

import org.junit.Test;

import base.menge.e.StichProbe;
import base.verteilung.Laplace;
import base.zv.Px;

public class ZufallsVariable_Test {
	@Test
	public void test(){
		StichProbe M3 = new  StichProbe(2, 10,true, new Laplace(2));
		Px X = new Px(M3, t->{
			double count = 0.;
			for(int w : t){
				count += w;
			}
			return count;
		});
		if(Px.Erwartungswert(X)!=5.){
			fail();
		}
		if(Px.Varianz(X)!=2.5){
			fail(""+Px.Varianz(X));
		}
		if(0.24609375!= X.dichte(5.)){
			fail();
		}
		//System.out.println(X.dichte(5.));
		//System.out.println(Px.Erwartungswert(X));
		//System.out.println(Px.Varianz(X));
	}
}
