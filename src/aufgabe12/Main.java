package aufgabe12;

import VL1.LogCat;

/**
 * Klasse enthält die vorgegebene Static Methode, die abfragt, ob das erste Element eine Zahl ist.
 * @author speters
 *
 */

public class Main {
	/*
	 * Hilfs konstanten zum Deuggen
	 */
	private static final String TAG = Main.class.getSimpleName();
	
	/**
	 * Testet ob das erste Element der Liste eine nummer ist
	 * @param liste Liste
	 * @return true wennn das erste Element eine Nummer ist ansosnten false;
	 */
	public static boolean firstIsNumber(ArrayListe<?> liste) {
		if(liste.getAnzahlElemente()>0) {
			try {
				Integer.valueOf(liste.get(0).toString());
			}catch(Exception e) {
				LogCat.e(e, TAG, e.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}

}
