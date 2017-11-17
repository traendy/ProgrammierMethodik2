package aufgabe21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
	
	private static tauscheUmlauteAus tausch = (String s) -> {
		StringBuilder bs = new StringBuilder();
		for(char c: s.toCharArray()) {
			
			switch (c){
				case 'Ä':
					bs.append("AE");
					break;
				case 'Ö':
					bs.append("OE");
					break;
				case 'Ü':
					bs.append("UE");
					break;
				case 'ß':
					bs.append("SS");
					break;
				default:
					bs.append(c);
					break;
				}
			
			}
		return bs.toString();
	};
	
	
	private static Kuerze kuerze = (String s) ->{
		if(s.length()>8) {
			char [] array = s.toCharArray();
			return String.copyValueOf(array, 0, 8);
		}
		return s;
	};
	
	
	static Predicate <String> isNull = String -> {
		return String != null;
		};	
	
	public static <T> void main (String[] args) {
		String[] kette = {
				" Sönke  ",
				"  dass",
				"daß ",
				null,
				"  Naemi ",
				"  Heizölrückstoßabdämpfung   ",
				"            Wenn alles einfach wäre "
		};
		korrigieren(kette).stream().forEach(System.out::println);
		}

	
	private static List<String> korrigieren(String[] kette) {
		ArrayList<String> liste = new ArrayList<>();			
		Arrays.stream(kette).filter(isNull).map(String::trim).map(String::toUpperCase).map(s -> tausch.tauscheAus(s)).map(s->kuerze.kuerze(s)).forEach(liste::add);
		return liste;
	}
	
	@FunctionalInterface
	public interface tauscheUmlauteAus{
		public String tauscheAus(String s);
	}

	@FunctionalInterface
	public interface Kuerze{
		public String kuerze(String s);
	}
	
	
}
