package aufgabe3;

import java.util.Random;

/**
 * Lokführerklasse
 * Thread der zufällig entscheidet ob er versucht einen Zug aus dem Bahnhof zu hohlen oder einen abzustellen.
 * @author speters
 *
 */

public class Lokfuehrer extends Thread{
	/**
	 * Bahnhof an dem der Zugführer arbeitet
	 */
	private RangierBahnhof bahnhof;
	private int anzahlDerGleise;
	
	//Random zur Entscheidungsfindung
	private Random random = new Random();
	
	
	
	/**
	 * Konstruktor
	 * @param bahnhof Bahnhof an dem der Lokführer angestellt ist
	 * @param anzahlDerGleise Anzahl der Gleise an dem Bahnhof
	 */
	public Lokfuehrer (RangierBahnhof bahnhof, int anzahlDerGleise) {
		this.bahnhof = bahnhof;
		this.anzahlDerGleise = anzahlDerGleise;
	}

	
	/**
	 * Run Methode
	 */
	@Override 
	public void run() {
	
		int aufgabenWahl = random.nextInt(2);
		switch(aufgabenWahl) {
		//Lokführer versucht einen Zug auf das Gleis zu bringen
		case 0:
			bahnhof.zugEinfahren(bahnhof.new Zug(), random.nextInt(anzahlDerGleise), this.getId());
			break;
			
		//Lokführer versucht einen Zug vom Gleis zu entfernen
		case 1:
			bahnhof.zugAusfahren(random.nextInt(anzahlDerGleise), this.getId());
			break;
		//Fallback
		default:
			System.out.println("falscher Random wert: int aufgabenWahl = " + aufgabenWahl);
			break;
		}
		
		
	}
	
	

}
