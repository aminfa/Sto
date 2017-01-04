package base.menge;

import java.util.ArrayList;

import base.menge.e.ESet;
import base.menge.e.Ereignis;
import base.menge.e.EreignisMenge;
import base.structure.Tupel;

public abstract class Menge implements Iterable<Tupel> {
	//-1 = infinite 
	
	public static EreignisMenge VEREIN(EreignisMenge a , EreignisMenge b){
		if(!SameMenge(a,b)) return null;
		ArrayList<Tupel> set = new ArrayList<Tupel>();
		for(Tupel t : a){
			set.add(t);
		}
		for(Tupel t : DIFF(b,a)){
			if(!a.isIn(t)){
				set.add(t);
			}
		}
		return new ESet(a.gm,a.times(),set);
	}
	public static EreignisMenge SCHNITT(EreignisMenge a , EreignisMenge b){
		if(!SameMenge(a,b)) return null;
		ArrayList<Tupel> set = new ArrayList<Tupel>();
		for(Tupel t : a){
			if(b.isIn(t))
				set.add(t);
		}
		return new ESet(a.gm,a.times(),set);
	}
	public static EreignisMenge DIFF(EreignisMenge a , EreignisMenge b){
		if(!SameMenge(a,b)) return null;
		ArrayList<Tupel> set = new ArrayList<Tupel>();
		for(Tupel t : a){
			if(!b.isIn(t))
				set.add(t);
		}
		return new ESet(a.gm,a.times(),set);
	}
	public static EreignisMenge BEDINGUNG(EreignisMenge a , EreignisMenge b){
		return new ESet(b, a.times(),SCHNITT(a,b));
	}
	@SuppressWarnings("unused")
	private static Ereignis VEREIN(Ereignis a, Ereignis b, Ereignis a_und_b){
		return (()->a.p() + b.p() - a_und_b.p());
	}
	@SuppressWarnings("unused")
	private static Ereignis SCHNITT(Ereignis A, Ereignis B_bed_A){
		return ()->A.p()*B_bed_A.p();
	}
	@SuppressWarnings("unused")
	private static Ereignis BEDINGUNG( Ereignis b, Ereignis a_und_b){
		return () -> a_und_b.p() / b.p();
	}
	public static boolean SameMenge(EreignisMenge a , EreignisMenge b){
		if(a.gm!=b.gm)return false;
		if(a.times()!=b.times()) return false;
		return true;
	}
}
