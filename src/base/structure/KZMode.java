package base.structure;

/**
 * Kein zurueck legen.
 *
 */
public class KZMode  implements DrawMode{
	@Override
	public boolean nextInLine(Tupel t, int v) {
		for(int i : t)
			if(v==i) return false;
		return true;
	}

}
