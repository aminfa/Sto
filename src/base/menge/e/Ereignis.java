package base.menge.e;

import base.menge.Menge;

public interface Ereignis {
	public double p();
	public static boolean DISJUNKT(EreignisMenge A, EreignisMenge B){
		return Menge.SCHNITT(A, B).p() == 0;
	}
	public static boolean UNABHAENGIG(EreignisMenge A, EreignisMenge B){
		return Menge.SCHNITT(A,B).p() == A.p() * B.p();
	}
}
