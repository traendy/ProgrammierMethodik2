package aufgabe21;

import static org.junit.Assert.*;
/**
 * Test Klasse für die Lamda und Stream Aufgabe des zweiten Blattes
 * "dont test anything that is too small to break"
 */

import java.util.List;

import org.junit.Test;

public class MainTest {

	/**
	 * Testet die Methode korrigieren mit verschiedesten Arrays.
	 */
	@Test
	public void korrigierentest() {
		//leeres Array
		String leeresArray[] = new String[0];
		assertEquals(0, Main.korrigieren(leeresArray).size());
		
		//array nur mit null Einträgen
		String nullArray[] = new String [234];
		assertEquals(0, Main.korrigieren(nullArray).size());
		
		//array mit Zahlen
		String zahlArray[] = new String[1];
		zahlArray[0]="1";
		assertEquals(1, Main.korrigieren(zahlArray).size());
		assertEquals("1", Main.korrigieren(zahlArray).get(0));
		
		//array mit Umlauten
		String umlautArray[] = {"Ä", "Ö","Ü","ß", "ä", "ö","ü" };
		assertEquals(7, Main.korrigieren(umlautArray).size());
		assertEquals("AEOEUESSAEOEUE", listToString(Main.korrigieren(umlautArray)));
		
		//array mit leerzeichen
		String leerzeichenArray[] = {" ", "  ", " a ", " l   ", " a  k  "};
		assertEquals(5, Main.korrigieren(leerzeichenArray).size());
		assertEquals("ALA  K", listToString(Main.korrigieren(leerzeichenArray)));
		
		//array mit Kleinbuchstaben
		String kleinbuchstabenArray [] = {"a", "b", "c"};
		assertEquals(3, Main.korrigieren(kleinbuchstabenArray).size());
		assertEquals("ABC", listToString(Main.korrigieren(kleinbuchstabenArray)));
		
		//array mit sehr langen Wörtern
		String langWortArray[] = {	"Heizölrückstoßabdämpfung",
									"Mehrwegflasche",
									"Orientexpress",
									"Wankelmotor",
									"Apfelbaum",
									"Marathon"};
		assertEquals(6, Main.korrigieren(langWortArray).size());
		for(String s: Main.korrigieren(langWortArray)) {
			assertEquals(8, s.length());
		}
		assertEquals("HEIZOELRMEHRWEGFORIENTEXWANKELMOAPFELBAUMARATHON", listToString(Main.korrigieren(langWortArray)));
		
		//arrayGemischt
		String[] gemischtesArray = {
				" Sönke  ",
				"  dass",
				"daß ",
				null,
				"  Naemi ",
				"  Heizölrückstoßabdämpfung   ",
				"            Wenn alles einfach wäre "
		};
		assertEquals(6, Main.korrigieren(gemischtesArray).size());
		assertEquals("SOENKEDASSDASSNAEMIHEIZOELRWENN ALL", listToString(Main.korrigieren(gemischtesArray)));
		
	}
	
	private String listToString(List<String> array) {
		StringBuilder sb  = new StringBuilder();
		array.stream().forEach(sb::append);
		return sb.toString();
	}

}
