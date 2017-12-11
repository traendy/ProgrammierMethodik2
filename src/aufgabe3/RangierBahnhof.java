package aufgabe3;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * Rangierbahnhofklasse Als Task implementiert Der Randgierbahnhof erstellt
 * allle 500ms einen neuen Lokführer, der dort arbeitet AuÃŸerdem kÃ¼mmert sich
 * der Bahnhof darum, dass immer nur ein Gleis benutzt werden kann.
 * 
 * @author speters
 *
 */
public class RangierBahnhof extends Observable implements Runnable {
	// Anzahl der Gleise
	public static final int GLEIS_ANZAHL = 10;
	// Array mit Zügen
	private Zug[] gleise;
	private int gleisNummer;
	// Hilfsvariablen
	private int counts = 0;
	private static Object gleis = new Object();
	private String ausgabe = "";
	// Puffer
	private final List<Zug> puffer;

	/**
	 * Aktuelle Anzahl von ELementen im Puffer.
	 */
	@SuppressWarnings("unused")
	private int anzahlElemente = 0;

	/**
	 * Konstruktor
	 * 
	 * @param grid
	 *            Gridpane das den Bahnhof darstellt
	 */
	public RangierBahnhof() {
		
		puffer = new ArrayList<>();
		anzahlElemente = 0;

		for (int i = 0; i < GLEIS_ANZAHL; i++) {
			puffer.add(null);
		}

		gleise = new Zug[GLEIS_ANZAHL];

	}

	/**
	 * Methode zum einfahren eines Zuges. Wenn das Gleis frei ist wird ein Zug in
	 * den Bahnhof reingefahren. Ansonsten wird gewartet.
	 * 
	 * @param zug
	 *            Zug der in den Bahnhof einfahren soll.
	 * @param gleisNummer
	 *            Position an der der Zug einfahren soll
	 * @param id
	 *            id des Lokführers für die Ausgabe
	 */
	public /* synchronized */void zugEinfahren(Zug zug, int gleisNummer, long id) {
		/*
		 * while (anzahlElemente == puffer.size() && puffer.get(gleisNummer)!=null) {
		 * try { this.wait(); } catch (InterruptedException e) {
		 * Thread.currentThread().interrupt(); return; } }
		 * 
		 * puffer.set(anzahlElemente, zug);
		 * 
		 * ausgabe = "Zug wurde auf Gleis: " + gleisNummer + " durch " + id +
		 * " abgestellt."; try { Thread.sleep(500); } catch (InterruptedException e) { }
		 * System.out.println(ausgabe); updateGridPane(-1); anzahlElemente++;
		 * 
		 * System.err.println("---\nNeuer Pufferinhalt: " + this); this.notifyAll();
		 */
		synchronized (gleis) {

			while (gleise[gleisNummer] != null) {
				try {
					gleis.wait();
				} catch (InterruptedException e) {
					return;
				}
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			gleise[gleisNummer] = zug;

			ausgabe = "Zug wurde auf Gleis: " + gleisNummer + " durch " + id + " abgestellt.";
			System.out.println(ausgabe);

			// updateGridPane(-1);
			setGleisNummer(-1);
			setChanged();
			notifyObservers();
			// Monitor benachrichtigen
			gleis.notifyAll();
			return;

			// this.getClass().notify();
		}

	}

	/**
	 * Methode zum Ausfahren eines Zuges. Wenn das Gleis frei ist,und ein Zug in dem
	 * Array an der gleisNummer ist, wird ein Zug aus dem Array genommen und aus dem
	 * Bahnhof ausgefahren.
	 * 
	 * @param gleisNummer
	 *            Position des Arrays an der der Lokführer auf den Zug wartet
	 * @param id
	 *            Kennnummer des Lokführers
	 * @return Zug den der Lokführer aus dem Bahnhof geholt hat
	 */
	public /* synchronized */ Zug zugAusfahren(int gleisNummer, long id) {
		/*
		 * while (anzahlElemente == 0 && puffer.get(gleisNummer) == null) { try {
		 * this.wait(); } catch (InterruptedException e) {
		 * Thread.currentThread().interrupt(); return null; } } Zug zug =
		 * puffer.get(anzahlElemente - 1); anzahlElemente--; ausgabe =
		 * "Zug wurde von Gleis: " + gleisNummer + " durch " + id + " entfernt."; try {
		 * Thread.sleep(500); } catch (InterruptedException e) { }
		 * System.out.println(ausgabe); updateGridPane(gleisNummer);
		 * 
		 * System.err.println("Neuer Pufferinhalt: " + this);
		 * 
		 * this.notifyAll(); return zug;
		 */

		synchronized (gleis) {

			while (gleise[gleisNummer] == null) {
				try {
					gleis.wait();
				} catch (InterruptedException e) {
					return null;
				}
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			ausgabe = "Zug wurde von Gleis: " + gleisNummer + " durch " + id + " entfernt.";

			System.out.println(ausgabe);
			gleise[gleisNummer] = null;
			setGleisNummer(gleisNummer);
			// updateGridPane(gleisNummer);
			setChanged();
			notifyObservers();
			// Monitor benachrichtigen
			gleis.notifyAll();
			return gleise[gleisNummer];

			// this.getClass().notify();

		}

	}

	/**
	 * Call Methode des Tasks Alle 500 ms wird ein neuer Lokfuehrer erstellt und
	 * gestartet
	 */
	@Override
	public void run() {
		while (true) {
			System.out.println(counts);
			counts++;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Lokfuehrer lokfuehrer = new Lokfuehrer(this, GLEIS_ANZAHL);

			lokfuehrer.start();

		}

	}

	/**
	 * Hilfsmethode für die Konsolenausgabe
	 */
	public String toString() {
		return ausgabe;
	}

	/**
	 * Hilfsmethode Gibt eine Liste mit allen GLeisen zurück abh. ob es Besetzt ist
	 * oder nicht
	 * 
	 * @return Liste mit "leer" wenn in dem Array kein Zug ist, ansonsten
	 *         "Zug"+GLeisnummer
	 */
	public ArrayList<String> getGleise() {
		ArrayList<String> zuege = new ArrayList<String>();
		for (int i = 0; i < GLEIS_ANZAHL; i++) {
			if (gleise[i]/* puffer.get(i) */ != null) {
				zuege.add("Zug " + i);
			} else {
				zuege.add("leer");
			}
		}
		return zuege;
	}

	/**
	 * Setter für das Gleisarray
	 * 
	 * @param gleise
	 *            Array von Zügen
	 */
	public void setGleise(Zug[] gleise) {
		this.gleise = gleise;
	}

	/**
	 * Getter für das Gleisarray
	 * 
	 * @param gleisNummer
	 *            Gleis Nummer
	 */
	public int getGleisNummer() {
		return gleisNummer;
	}

	/**
	 * Setter für das Gleisarray
	 * 
	 * @param gleisNummer
	 *            Gleis Nummer
	 */
	public void setGleisNummer(int gleisNummer) {
		this.gleisNummer = gleisNummer;
	}

	/**
	 * Innere Klasse Zug
	 * 
	 * @author speters
	 *
	 */
	public final class Zug {

	}

}
