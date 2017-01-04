package base.menge.e;

import java.util.Iterator;
import java.util.function.Function;

import base.menge.GrundMenge;
import base.structure.Baum;
import base.structure.DrawMode;
import base.structure.KZMode;
import base.structure.Tupel;

public class EDraw extends EreignisMenge {
	private Function<Tupel, Boolean> fun;
	private double sized=-1;
	private boolean zuruck;
	private DrawMode drawMode;
	EDraw(GrundMenge gm,int t, boolean zuruck, Function<Tupel, Boolean> f) {
		this(gm,t,
				(zuruck?
						(tupel,i)->true:
							new KZMode()),
				f);
	}
	public EDraw(GrundMenge gm,int t, DrawMode dm, Function<Tupel, Boolean> f) {
		super(gm ,t);
		fun  =f;
		drawMode = dm;
		if(drawMode instanceof KZMode){
			zuruck = false;
		}else
			zuruck = true;
	}

	@Override
	protected boolean elementOf(Tupel t) {
		return fun.apply(t);
	}

	

	@Override
	public Iterator<Tupel> iterator() {
		if(zuruck){
			return new Baum(M(), times(),t -> isIn(t), (t,i)-> true);
		}
		else 
			return new Baum(M(), times(),t -> isIn(t), new KZMode());
	}
	
	
	@Override
	public double size() {
		if(sized!=-1)return sized;
		sized = 0;
		for(@SuppressWarnings("unused") Tupel t : this){
			sized++;
		}
		return size();
	}

	@Override
	public double p() {
		double p= 0.;
		for(Tupel t : this){
			p+= gm.p(t);
		}
		return p;
	}

}
