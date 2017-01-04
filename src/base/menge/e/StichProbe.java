package base.menge.e;

import java.util.Iterator;
import java.util.function.Function;

import base.menge.GrundMenge;
import base.structure.DrawMode;
import base.structure.Faktoriel;
import base.structure.Linear;
import base.structure.Tupel;
import base.verteilung.Verteilung;
import base.verteilung.Verteilung_KZ;

public class StichProbe extends GrundMenge{
	private boolean zuruck;
	private Verteilung v;
	private int times = 1;
	private int M;
	public StichProbe(int M, int t, boolean zurucklegen, Verteilung v) {
		this.M = M;
		zuruck= zurucklegen;
		this.v = zuruck ? 
				v : 
				new Verteilung_KZ(v);
		setTimes(t);
	}
	public int getTimes(){
		return times;
	}
	public void setTimes(int t){
		if(t>0)
			times = t;
	}

	@Override
	public Iterator<Tupel> iterator() {
		if(times ==-1) return new Linear(M,1);
		return powerT(times).iterator();
	}
	public double size() {
		return base.numerik.Num.DRAW(M, times, !zuruck);
	}
	public double p(int i){
		return v.p(i);
	}
	public EDraw draw(Function<Tupel, Boolean> f){
		return new EDraw(this,times,zuruck,f);
	}
	public EDraw draw(int times,Function<Tupel, Boolean> f){
		return new EDraw(this,times,zuruck,f);
	}
	public EDraw draw(DrawMode dm, Function<Tupel, Boolean> f){
		return new EDraw(this,times,dm,f);
	}
	//@Override
	private Iterable<Tupel> powerT(int t) {
		return new DummyItClass(t);
	}
	class DummyItClass implements Iterable<Tupel>{
		int times ;
		DummyItClass(int t){
			times =t ;
		}
		@Override
		public Iterator<Tupel> iterator() {
			if(zuruck){
				return new Linear(M,times);
			}
			else{
				return new Faktoriel(M,times);
			}
		}
	}
	@Override
	public double p(Tupel t) {
		return v.p(t);
	}
	@Override
	public int M() {
		return M;
	}
	public int T(){
		return times;
	}
}
