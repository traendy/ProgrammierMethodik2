package aufgabe12;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Testklasse für die ArrayListe
 * @author speters
 *
 */
public class ArrayListeTest {
	
	private ArrayListe arrayListeUnderTest;
	
	/**
	 * Testet den Konstruktor auf diverse Objecte
	 */
	@Test
	public void ConstructorTest() {
		arrayListeUnderTest = new ArrayListe<String>();
		assertNotNull(arrayListeUnderTest);
		arrayListeUnderTest = new ArrayListe<Integer>();
		assertNotNull(arrayListeUnderTest);
		arrayListeUnderTest = new ArrayListe<Character>();
		assertNotNull(arrayListeUnderTest);
		arrayListeUnderTest = new ArrayListe<Float>();
		assertNotNull(arrayListeUnderTest);
		arrayListeUnderTest = new ArrayListe<Double>();
		assertNotNull(arrayListeUnderTest);
		//arrayListeUnderTest = new ArrayListe< Object>();
		//assertNotNull(arrayListeUnderTest);
	}
	
	/**
	 * Testet die hinzufügen Methode
	 */
	@Test
	public void hinzufuegenTest() {
		arrayListeUnderTest = new ArrayListe<String>();
		assertEquals(0, arrayListeUnderTest.getAnzahlElemente());
		arrayListeUnderTest.hinzufeuegen("TEST");
		assertEquals(1, arrayListeUnderTest.getAnzahlElemente());
		arrayListeUnderTest.hinzufeuegen("TEST");
		assertEquals(2, arrayListeUnderTest.getAnzahlElemente());
		arrayListeUnderTest.hinzufeuegen("TEST");
		assertEquals(3, arrayListeUnderTest.getAnzahlElemente());
		arrayListeUnderTest.hinzufeuegen("TEST");
		assertEquals(4, arrayListeUnderTest.getAnzahlElemente());
	}
	
	/**
	 * Testet die GetMethode
	 */
	@Test
	public void getTest() {
		arrayListeUnderTest = new ArrayListe<String>();
		arrayListeUnderTest.hinzufeuegen("TEST0");
		arrayListeUnderTest.hinzufeuegen("TEST1");
		arrayListeUnderTest.hinzufeuegen("TEST2");
		assertEquals("TEST1", arrayListeUnderTest.get(1));
		assertNull(arrayListeUnderTest.get(1337));
		assertNull(arrayListeUnderTest.get(-1337));
		}
	
	/**
	 * Testet die entfernen Methode
	 */
	@Test
	public void entferneTest() {
		arrayListeUnderTest = new ArrayListe<String>();
		arrayListeUnderTest.hinzufeuegen("TEST0");
		arrayListeUnderTest.hinzufeuegen("TEST1");
		arrayListeUnderTest.hinzufeuegen("TEST2");
		arrayListeUnderTest.hinzufeuegen("TEST3");
		arrayListeUnderTest.hinzufeuegen("TEST4");
		
		arrayListeUnderTest.entferne("TEST4");
		assertEquals(4, arrayListeUnderTest.getAnzahlElemente());
		
		arrayListeUnderTest.entferne("TEST2");
		assertEquals(3, arrayListeUnderTest.getAnzahlElemente());
		
		arrayListeUnderTest.entferne("TEST0");
		assertEquals(2, arrayListeUnderTest.getAnzahlElemente());
		
		arrayListeUnderTest.entferne("TEST1");
		assertEquals(1, arrayListeUnderTest.getAnzahlElemente());
		
		arrayListeUnderTest.entferne("TEST3");
		assertEquals(0, arrayListeUnderTest.getAnzahlElemente());
		
		arrayListeUnderTest.entferne("TEST4");
		assertEquals(0, arrayListeUnderTest.getAnzahlElemente());
		
		
	}
	
	/**
	 * Testet die entfernen an Position Methode
	 */
	@Test
	public void entfeneElementAnPositionsTest() {
		arrayListeUnderTest = new ArrayListe<String>();
		arrayListeUnderTest.hinzufeuegen("TEST0");
		arrayListeUnderTest.hinzufeuegen("TEST1");
		arrayListeUnderTest.hinzufeuegen("TEST2");
		arrayListeUnderTest.hinzufeuegen("TEST3");
		arrayListeUnderTest.hinzufeuegen("TEST4");
		
		arrayListeUnderTest.entferneElementAnIndex(4);
		assertEquals(4, arrayListeUnderTest.getAnzahlElemente());
	
		arrayListeUnderTest.entferneElementAnIndex(2);
		assertEquals(3, arrayListeUnderTest.getAnzahlElemente());
	
		arrayListeUnderTest.entferneElementAnIndex(0);
		assertEquals(2, arrayListeUnderTest.getAnzahlElemente());
	
		arrayListeUnderTest.entferneElementAnIndex(1337);
		assertEquals(2, arrayListeUnderTest.getAnzahlElemente());
	
		arrayListeUnderTest.entferneElementAnIndex(-10);
		assertEquals(2, arrayListeUnderTest.getAnzahlElemente());
	
	}
	
	/**
	 * Testet ob, das erste Element eine Zahll ist Mehode
	 */
	@Test
	public void erstesElementIstZahlTest() {
		arrayListeUnderTest = new ArrayListe<Integer>();
		arrayListeUnderTest.hinzufeuegen(new Integer(3));
		arrayListeUnderTest.hinzufeuegen(new Integer(3));
		arrayListeUnderTest.hinzufeuegen(new Integer(3));
		arrayListeUnderTest.hinzufeuegen(new Integer(3));
		arrayListeUnderTest.hinzufeuegen(new Integer(3));
		assertTrue(Main.firstIsNumber(arrayListeUnderTest));
		arrayListeUnderTest = new ArrayListe<String>();
		arrayListeUnderTest.hinzufeuegen("TEST0");
		arrayListeUnderTest.hinzufeuegen("TEST1");
		arrayListeUnderTest.hinzufeuegen("TEST2");
		arrayListeUnderTest.hinzufeuegen("TEST3");
		arrayListeUnderTest.hinzufeuegen("TEST4");
		assertFalse(Main.firstIsNumber(arrayListeUnderTest));
		
	}
	
	@Test
	public void getkleinstesElementTest() {
		arrayListeUnderTest = new ArrayListe<Integer>();
		arrayListeUnderTest.hinzufeuegen(new Integer(1));
		arrayListeUnderTest.hinzufeuegen(new Integer(3));
		arrayListeUnderTest.hinzufeuegen(new Integer(7));
		assertEquals(1, arrayListeUnderTest.getKleinstesElement());
	}
	

}
