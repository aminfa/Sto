package bs;

import static org.junit.Assert.*;

import org.junit.Test;

import base.menge.e.EreignisMenge;
import base.menge.e.StichProbe;
import base.menge.e.EDraw;
import base.p.Wahr;
import base.structure.Tupel;
import base.verteilung.Laplace;

public class UrneTest {
	StichProbe urne;
	@Test
	public void test() {
		int white = 7;
		int black = 3;
		int size = white + black;
		int zieh = 2;
		int zug = zieh;
		
		//0-6 white
		//7-9 black
		urne = new StichProbe(size,zieh,false,new Laplace(size) );
		// Es wird genau eine weisse gezogen
		EDraw white1 = urne.draw((Tupel t) ->{
			boolean w = false;
			for(int i : t){
				if(i < white){
					if(!w)  w = true;
					else return false;
				}
			}
			if(!w&&t.n<zug){
				return true;
			}
			return w;
		});
		if(!Wahr.eq(white1.p() , 42./90.)) fail();
		zieh  = 6;
		EreignisMenge allWhite = urne.draw(zieh,(Tupel t) ->{
			for(int i : t){
				if(i >= white){
					return false;
				}
			}
			return true;
		});
		if(!Wahr.eq(allWhite.p(),5040.0/151200.0))fail();
	}

}
