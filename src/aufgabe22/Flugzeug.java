package aufgabe22;

import java.util.Random;

/**
 * Flugzeug Klasse, repräsentiert ein Flugzeug in der Näche eines Flughafens
 * 
 * @author speters
 *
 */
public class Flugzeug implements Runnable {
	// Zeit bis zur Landung
	private static final long LANDUNG = 1500;

	// Variablen zur Berechnung des Flugstatus
	private long flugzeit = 0;
	private long createdAt;
	private long airportTime = 0;

	// Flugstatus
	public FlugStatus status = FlugStatus.IM_FLUG;

	// Zeigt an ob der Thread noch läuft
	private boolean running = false;

	// Nummer des Flugzeugs zur Identifikation
	private int flugnummer = 0;

	/**
	 * Konstruktor
	 * 
	 * @param createdAt
	 *            Zeitpunkt an dem Das Flugzeug aufgetaucht ist in long
	 */
	public Flugzeug(long createdAt) {
		Random r = new Random();
		// Flugzeit von zwischen 1 und 10 Sekunden
		this.flugzeit = r.nextInt(10000) + 1000;
		// Zufällige Flugnummer
		this.flugnummer = r.nextInt(1000);
		// Zeit zu der das Flugzeug erstellt wurde
		this.createdAt = createdAt;
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			if (status == FlugStatus.GELANDET) {
				running = false;
			}
		}
	}

	/**
	 * Gibt zurück, ob der Thead noch läuft
	 * 
	 * @return true, wenn der Thread noch läuft sonst false
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Getter für die Flugnummer
	 * 
	 * @return Flugnummmer als Integer
	 */
	public int getFlugnummer() {
		return flugnummer;
	}

	/**
	 * Aktualisiert die Uhrzeit auf dem FLugzeug und den Status des Flugzeuges
	 * 
	 * @param airportTime
	 *            Uhrzeit, wird vom Flughafen übergeben
	 */
	public void updateAirportTime(long airportTime) {
		this.airportTime = airportTime;
		if ((createdAt + flugzeit) - airportTime <= 0) {
			if ((createdAt + flugzeit + LANDUNG) - airportTime <= 0) {
				status = FlugStatus.GELANDET;
			} else {
				status = FlugStatus.IM_LANDEANFLUG;
			}
		}
	}

	/**
	 * To String Methode für die Ausgabe des Programms
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Flugnummer: ").append(flugnummer).append(" \tStatus:  ").append(status).append(" \tFlugzeit: ")
				.append(flugzeit).append(" \tLebenszeit: ").append((System.currentTimeMillis() - createdAt))
				.append(" \tZeit bis zur Landung: ").append((createdAt + flugzeit) - airportTime);
		return sb.toString();
	}

}
