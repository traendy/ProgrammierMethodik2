package aufgabe22;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Main Klasse
 * 
 * @author speters
 *
 */

public class Main {
	// Maximale Anzahl an Flugzugen, die der Flughafen managen kann
	private static final int NUMBER_OF_PLANES = 5;

	/**
	 * Main Methode Initialisiert den Flughaven mit der ersten Generation von
	 * Flugzeugen und startet den Flughafen-Thread
	 * 
	 * @param args
	 *            no params
	 */
	public static void main(String[] args) {
		ArrayList<Flugzeug> FlugzeugListe = new ArrayList<>();

		for (int i = 0; i < NUMBER_OF_PLANES; i++) {
			FlugzeugListe.add(new Flugzeug(System.currentTimeMillis()));
		}
		Flughafen flughafen = new Flughafen(FlugzeugListe);
		Thread t = new Thread(flughafen);
		t.start();
	}

}
