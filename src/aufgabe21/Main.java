package aufgabe21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Main Klasse der Aufgabe 1 des zweiten Aufgabenblattes
 * @author Söenke Peters
 * @author 
 *
 */
public class Main {
	/**
	 * Methode zum Umlauteaustauschen
	 */
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
	
	/**
	 * Methode zum Kürzen eines Strings auf die Länge 8
	 */
	private static Kuerze kuerze = (String s) ->{
		if(s.length()>8) {
			char [] array = s.toCharArray();
			return String.copyValueOf(array, 0, 8);
		}
		return s;
	};
	
	/**
	 * Predicate für die Null-Abfrage
	 */
	static Predicate <String> isNull = String -> {
		return String != null;
		};	
	
		
	/**
	 * Main Methode des Programms,
	 * erstellt ein String array und führt die Funktion korrigieren aus	
	 * @param args
	 */
	public static void main (String[] args) {
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

	/**
	 * Methode ruft die vorgebenen Umwandlungen auf, die auf das String Array ausgeführt werden sollen.
	 * @param kette String array
	 * @return Liste mit Strings aus dem Stringarray
	 */
	public static List<String> korrigieren(String[] kette) {
		ArrayList<String> liste = new ArrayList<>();			
		Arrays.stream(kette).filter(isNull).map(String::trim).map(String::toUpperCase).map(s -> tausch.tauscheAus(s)).map(s->kuerze.kuerze(s)).forEach(liste::add);
		return liste;
	}
	
	/**
	 * Functional Interface für das Umlauttauschen
	 * @author speters
	 *
	 */
	@FunctionalInterface
	public interface tauscheUmlauteAus{
		public String tauscheAus(String s);
	}

	/**
	 * FunctionalInterface für das Kuerzen der Strings
	 * @author speters
	 *
	 */
	@FunctionalInterface
	public interface Kuerze{
		public String kuerze(String s);
	}
	
	
}
