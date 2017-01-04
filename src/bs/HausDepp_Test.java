package bs;
 import static org.junit.Assert.fail;
 import org.junit.Test;

import base.menge.e.EDraw;
import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.verteilung.Laplace;
public class HausDepp_Test {
	@Test
	public void test(){
		int m =2;
		StichProbe deck =  new StichProbe(4*m,4*m,false,new Laplace(4*m));
		EreignisMenge[] spieler = new EreignisMenge[m];
		for(int i = 0 ; i < m ; i++){
			int sIndex = i;
			spieler[i] = deck.draw(t -> {
				if(t.n <(sIndex *4)+1) return true;
				int quartet = -1;
				for(int j = sIndex *4;  j < t.n && j < (sIndex+1) *4; j++){
					int karte = t.get(j);
					if(quartet == -1){
						quartet = karte  % m;
					}
					else if(karte  % m != quartet){
						return false;
					}
				}
				return true;
			});
		}
		//System.out.println(spieler[0].p());
	}
}
