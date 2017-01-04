package base.p;

import java.util.function.Function;

import base.menge.e.EreignisMenge;
import base.structure.Baum;
import base.structure.DrawMode;
import base.structure.Tupel;
import base.verteilung.Verteilung;

public abstract class Wahr {
	public static double P(Verteilung v, EreignisMenge E) {
		double p = 0.;
		for(Tupel t : E){
			if(E.isIn(t)){
				System.out.println(t);
				p+=v.p(t);
			}
		}
		
		return p;
	}
	/**
	 * 
	 * @param E
	 * @param xIn xIn returns true if all possible subtrees of the tupen is in the event.
	 * @return
	 */
	private static double P_EFF(EreignisMenge E, DrawMode im, Function<Tupel,Boolean> xIn) {
		Baum baum = new Baum(0, E.times(), t -> { return E.isIn(t); }, im, xIn);
		double p = 0.;
		while(baum.hasNext()){
			Tupel t = baum.next();
			//System.out.println(t + " " + E.gm.p(t));
			p  += E.gm.p(t);
		}
		return p;
		
	}
	public static String P_LAP_print(EreignisMenge E){
		return "" +  E.size() + "/" + E.gm.size();
	}
	public static double P_LAP(EreignisMenge E) {
		/*
		double size = E.gm.size(E.times());
		double counter = 0;
		for(Tupel t : E){
			counter++;
		}
		return counter / size;*/
		return E.size() / E.gm.size();
	}
	public static boolean eq(double a, double b){
		if(Math.abs(a-b)<0.0001) return true;
		return false;
	}
}
