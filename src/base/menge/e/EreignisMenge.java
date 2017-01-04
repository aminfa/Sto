package base.menge.e;
import base.menge.GrundMenge;
import base.structure.Tupel;
public abstract class EreignisMenge extends GrundMenge  implements Ereignis {
	
	/**
	 * Sagt aus wie OFT gm ausgeführt wird.
	 * Beispiel: grundmenge = Wurfel = {1..6} n = 2 -> 2 mal wuerfel. 
	 * Tupel length = n;
	 */
	private final int n;
	public final GrundMenge gm;
	public EreignisMenge(GrundMenge gm, int times) {
		n = times;
		this.gm = gm;
	}
	public int times(){
		return n;
	}
	/**
	 * Zeigt ob ein tupel enthalten ist. 
	 * Falls die groesse des tupels kleiner ist als die anzahl an
	 *  versuchen (ntimes), wird der tupel als ein Pfad in dem Baum betrachtet
	 *  und es wird inbaum betrachtet.
	 *  zb 3 mal wuerfeln is die grundmenge. Bei eingabe eines tupels mit 
	 *  2 zahlen wird false zurueck gegeben falls, die ergebnisse der beiden ersten 
	 *  das enthalten sein jedes tupels mit diese beide zahlen
	 *   ausschliesst.
	 *   
	 * @param t Tupel 
	 * @return ob dieser tupel enthalten ist.
	 */
	public final  boolean isIn(Tupel t){

		if(!tupelMatch(t)) return false;
		return elementOf(t);
	}
	protected abstract boolean elementOf(Tupel t) ;
	public boolean tupelMatch(Tupel t){
		return t.n <= times();
	}
	// Grundmenge extension
	@Override
	public double p(int i){
		return gm.p(i)/p();
	}
	@Override
	public double p(Tupel t){
		return gm.p(t)/p();
	}
	public int M(){
		return gm.M();
	}
	public int T(){
		return gm.T();
	}

}
