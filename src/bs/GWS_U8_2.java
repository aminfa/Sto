package bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

import base.menge.e.StichProbe;
import base.structure.Tupel;
import base.verteilung.Binomial;
import base.zv.Px;

public class GWS_U8_2 {
	
	static final int tiefe = 6;
	
	static final double gewinn = 10;
	
	static final double kosten(int a, int b) { return 2 * (b-a+1); }
	
	@Test
	public void auf2() {
		int n = tiefe + 1;
		

		StichProbe galtonBrett = new StichProbe(n, 1, true, Binomial.coinToss(n));
		
		for(Tupel t : galtonBrett) {
			System.out.println(String.format("P(X=%s) = %1.4f", t.get(0), galtonBrett.p(t)));
		}
		
		Px x6 = new Px(galtonBrett, t-> (double) t.get(0));
		
		System.out.println(String.format("E(X) = %1.2f", Px.Erwartungswert(x6)));
		System.out.println(String.format("V(X) = %1.2f", Px.Varianz(x6)));

		Map<Double, List<Tupel>> nutzungenId = alleEU(galtonBrett, Function.identity());
		
		
		System.out.println("\nfür U(x) = x" + formatErgebniss(nutzungenId));
		
		
		
//		Map<Double, List<Tupel>> nutzungenLog = alleEU(galtonBrett, 
//				x -> Math.log(x + 11));
//		
//		System.out.println("\nfür U(x) = ln(x + 1)" + formatErgebniss(nutzungenLog));
		
	}
	
	
	
	public Map<Double, List<Tupel>> alleEU(StichProbe galtonBrett, Function<Double, Double> nutzungsFkt) {

		Map<Double, List<Tupel>> ergebniss = new HashMap<>();
		
		for(int a = 0; a <= tiefe; a ++) {
			for(int b = a; b <= tiefe; b++) {
				Px p1 = EU(galtonBrett, nutzungsFkt, a,b);
				double nutzungErwartung = Px.Erwartungswert(p1);
//				System.out.println(String.format("Bereich {%d ... %d} gibst es nutzung von %0.4f.", a, b));
				// Ergbeniss eintragen:
				Tupel t = Tupel.bereichEinschliesslich(a,b);
				if(!ergebniss.containsKey(nutzungErwartung)) {
					List<Tupel> bereiche = new ArrayList<>();
					ergebniss.put(nutzungErwartung, bereiche);
				}
				ergebniss.get(nutzungErwartung).add(t);
			}
		}
		return ergebniss;
	}
	
	public Px EU(StichProbe galtonBrett,  Function<Double, Double> nutzungsFkt, int a, int b) {
		double kosten = kosten(a,b);
		
		return new Px(galtonBrett, t ->  {
			if(t.get(0)>=a && t.get(0) <= b) {
				return nutzungsFkt.apply((gewinn - kosten));
			} else {
				return nutzungsFkt.apply(0 - kosten);
			}
		});
	}
	
	public String formatErgebniss(Map<Double, List<Tupel>> map) {
		String s = "\nNutzung\t\tBereiche\n";
		s +=  map.keySet().stream().sorted().map(d -> String.format("% 1.3f\t\t%s", d,  
					map.get(d).stream().map(t -> t.toString()).collect(Collectors.joining(", ")))).
					collect(Collectors.joining("\n"));
		return s;
	}
}
