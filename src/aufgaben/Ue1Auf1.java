package aufgaben;

import static org.junit.Assert.*;

import org.junit.Test;

import base.menge.e.EDraw;
import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.p.Wahr;
import base.structure.Tupel;
import base.verteilung.Laplace;
import base.zv.Px;

public class Ue1Auf1 {

	@Test
	public void test() {
		StichProbe sp = new StichProbe(9, 6, true, new Laplace(9));
		EreignisMenge A = sp.draw(t -> {
			for(int i = 1; i <t.n ; i++){
				for(int j = 0; j < i; j++){
					if(t.get(i) == t.get(j)){
						return false;
					}
				}
			}
			return true;
		});
		System.out.println(Wahr.P_LAP_print(A) + "=" + Wahr.P_LAP(A));

		EreignisMenge B = sp.draw(t -> {
			for(int i = 1; i <t.n-1 ; i++){

				if(t.get(i) == t.get(i-1)){
					return false;
				}
				if(t.get(i) == t.get(i+1)){
					return false;
				}
			}
			return true;
		});
		System.out.println(Wahr.P_LAP_print(B) + "=" + Wahr.P_LAP(B));
		
		EreignisMenge C = sp.draw(t ->{
			int einsCount = 0;
			for(int i : t){
				if(i == 0){
					einsCount++;
				}
			}
			return einsCount <= 2;
		});
		System.out.println(Wahr.P_LAP_print(C) + "=" + Wahr.P_LAP(C));

		EreignisMenge D = sp.draw(t ->{
			if(t.n < 6)return true;
			int sum = 0;
			for(int i : t){
				sum+=i;
			}
			return sum == 13;
		});
		//System.out.println(Wahr.P_LAP_print(D) + "=" + Wahr.P_LAP(D));
		
	}

}
