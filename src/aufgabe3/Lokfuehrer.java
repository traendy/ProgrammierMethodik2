package aufgabe3;

import java.util.Random;


public class Lokfuehrer extends Thread{
	private RangierBahnhof bahnhof;
	private Random r = new Random();
	private int anzahlDerGleise;
	
	public Lokfuehrer (RangierBahnhof bahnhof, int anzahlDerGleise) {
		this.bahnhof = bahnhof;
		this.anzahlDerGleise = anzahlDerGleise;
	}

	@Override 
	public void run() {
	
		int aufgabenWahl = r.nextInt(2);
		switch(aufgabenWahl) {
		case 0:
			bahnhof.zugEinfahren(bahnhof.new Zug(), r.nextInt(anzahlDerGleise), this.getId());
			
			break;
		case 1:
			bahnhof.zugAusfahren(r.nextInt(anzahlDerGleise), this.getId());
		
			break;
		default:
			System.out.println("falscher Random wert: int aufgabenWahl = " + aufgabenWahl);
			break;
		}
		
		
	}
	
	

}
