package aufgabe22;

import java.util.List;

/**
 * Flughafen überwacht alle Fluzeuge und gibt ihnen die aktuelle Uhrzeit
 * 
 * @author speters
 *
 */
public class Flughafen implements Runnable {
	// List mit Flugzeugen
	List<Flugzeug> Flugzeuge;

	public Flughafen(List<Flugzeug> list) {
		Flugzeuge = list;
	}

	/**
	 * Es fehlt das die Flugzeit an des Flugzeug gemeldet wird und dass wenn ein
	 * Flugzeug geladet ist, ein neues Flugezeug erzeugt wird.
	 */
	@Override
	public void run() {
		int k = Flugzeuge.size();
		while (k > 0) {
			System.out.println("---------------------------------------------------------------------------------");
			k = Flugzeuge.size();
			for (int i = 0; i < Flugzeuge.size(); i++) {
				// update der Zeit in allen Flugzeugen
				Flugzeuge.get(i).updateAirportTime(System.currentTimeMillis());

				// Wenn ein Flugzeug thread noch nicht läuft, starte ihn
				if (!Flugzeuge.get(i).isRunning()) {
					Thread flugzeugThread = new Thread(Flugzeuge.get(i));
					flugzeugThread.start();
				}

				// Wenn ein Flugzeug gelandet ist, ersetze es.
				if (Flugzeuge.get(i).status == FlugStatus.GELANDET) {
					System.out.println("Flugzeug: " + Flugzeuge.get(i).getFlugnummer() + " ist sicher gelandet.\n");
					Flugzeug flugzeug = new Flugzeug(System.currentTimeMillis());
					Flugzeuge.remove(i);
					Flugzeuge.add(flugzeug);
				}
				// Gib die Daten des Flugzeugs aus.
				System.out.println(Flugzeuge.get(i).toString());
			}
			// Schlafe 500 ms
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
