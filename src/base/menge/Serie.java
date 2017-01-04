package base.menge;

import java.util.Iterator;
import base.structure.TTupel;
import base.structure.Tupel;

public class Serie {
	public final GrundMenge[] series;
	
	
	public Serie (GrundMenge gm, int times){
		series = new GrundMenge[times];
		for(int i = 0; i < times; i++){
			series[i]= gm;
		}
	}
	public Serie (GrundMenge...gms){
		series = gms;
	}
	public double p(TTupel tt){
		double p = 1.;
		for(int i = 0 ; i< tt.n ; i++){
			p *= series[i].p(tt.get(i));
		}
		return p;
	}
	public double size(){
		double s = 0;
		for(@SuppressWarnings("unused") TTupel tt : iterate()){
			s++;
		}
		return s;
	}
	public Iterable<TTupel> iterate() {
		return new SerieIt();
	}
	private class SerieIt implements Iterable<TTupel>, Iterator<TTupel>{
		private Iterator<Tupel>[] its;
		private Tupel[] ts = null;
		
		public SerieIt() {
			its = new Iterator[series.length];
			for(int i=0;i<its.length;i++){
				its[i] = series[i].iterator();
			}
			ts = new Tupel[its.length];
			for(int i = 0 ; i< its.length; i++){
				ts[i] = its[i].next();
			}
			
		}

		@Override
		public boolean hasNext() {
			for(int i = 0; i< ts.length; i++){
				if(ts[i]==null) return false;
			}
			return true;
		}

		@Override
		public TTupel next() {
			TTupel backup = TTupel.create(ts);
			for(int i = ts.length -1; i>= 0; i--){
				if(its[i].hasNext()){

					ts[i] = its[i].next();
					break;
				}
				else{
					its[i] = series[i].iterator(); 
					ts[i] = its[i].next();
					if(i == 0){
						ts[i] = null;
					}
				}
			}
			return backup;
		}

		@Override
		public Iterator<TTupel> iterator() {
			return this;
		}
		
	}
}
