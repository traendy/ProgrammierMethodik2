package aufgabe3;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Rangierbahnhofklasse Als Task implementiert Der Randgierbahnhof erstellt
 * allle 500ms einen neuen Lokführer, der dort arbeitet Außerdem kümmert sich
 * der Bahnhof darum, dass immer nur ein Gleis benutzt werden kann.
 * 
 * @author speters
 *
 */
public class RangierBahnhof extends Task<Object> {
	// Anzahl der Gleise
	public static final int GLEIS_ANZAHL = 10;
	// Array mit Zügen
	private Zug[] gleise;

	// Hilfsvariablen
	private int counts = 0;
	private static Object gleis = new Object();
	private String ausgabe = "";

	// GUI element
	private final GridPane grid;

	/**
	 * Konstruktor
	 * 
	 * @param grid
	 *            Gridpane das den Bahnhof darstellt
	 */
	public RangierBahnhof(GridPane grid) {
		gleise = new Zug[GLEIS_ANZAHL];
		this.grid = grid;
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
	public void zugEinfahren(Zug zug, int gleisNummer, long id) {

		synchronized (gleis) {
			boolean done = false;
			
			while (!done) {
				if (gleise[gleisNummer] == null) {
					done = true;
					gleise[gleisNummer] = zug;

					ausgabe = "Zug wurde auf Gleis: " + gleisNummer + " durch " + id + " abgestellt.";
					System.out.println(ausgabe);

					updateGridPane(-1);
					//Monitor benachrichtigen
					gleis.notify();
					return;
				} else {
					done = false;
					try {
						gleis.wait();
					} catch (InterruptedException e) {
						return;
					}
				}
			}
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
	public Zug zugAusfahren(int gleisNummer, long id) {

		synchronized (gleis) {
			boolean done = false;
			while (!done) {
				if (gleise[gleisNummer] != null) {
					done = true;
					ausgabe = "Zug wurde von Gleis: " + gleisNummer + " durch " + id + " entfernt.";

					System.out.println(ausgabe);
					gleise[gleisNummer] = null;

					updateGridPane(gleisNummer);
					//Monitor benachrichtigen
					gleis.notify();
					return gleise[gleisNummer];
				} else {
					done = false;
					try {
						gleis.wait();
					} catch (InterruptedException e) {
						return null;
					}
				}
			}
			// this.getClass().notify();

		}

		return gleise[gleisNummer];

	}

	/**
	 * Call Methode des Tasks Alle 500 ms wird ein neuer Lokfuehrer erstellt und
	 * gestartet
	 */

	@Override
	public Object call() {
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
	 * Updated das Gridpane, das den Bahnhof darstellt
	 * 
	 * @param gleisnummer
	 *            abhängig von der Gleisnummer wird der Bahnhof gezeichnet
	 *            (-1) Zug wurder eingefahren ansonsten (n) Gleisnummer n an der der neue Zug wartet
	 */
	private void updateGridPane(int gleisnummer) {

		System.out.println("update Grid");
		Platform.runLater(() -> {

			int i = 0;
			//Zug wurde ausgefahren und wird auf das Gleis gestellt
			if (gleisnummer > 0) {
				StackPane stackPane = new StackPane();
				Rectangle r = new Rectangle();
				r.setHeight(30);
				r.setWidth(60);
				r.setFill(Color.DARKSEAGREEN);

				stackPane.getChildren().addAll(r, new Label("Zug " + gleisnummer));
				grid.add(stackPane, 3, 1);

			}
			// NeuZeichnen aller eingefahrenen Züge
			for (String s : getGleise()) {

				StackPane stackPane = new StackPane();
				Rectangle r = new Rectangle();
				r.setHeight(30);
				r.setWidth(60);
				if (s.equals("leer")) {
					r.setFill(Color.GAINSBORO);
				} else {
					r.setFill(Color.BURLYWOOD);
				}

				stackPane.getChildren().addAll(r, new Label(s));
				grid.add(stackPane, 1, i);
				i++;
			}
		});

	}

	/**
	 * Hilfsmethode für die Konsolenausgabe
	 */
	public String toString() {
		return ausgabe;
	}

	/**
	 * Hilfsmethode
	 * Gibt eine Liste mit allen GLeisen zurück abh. ob es Besetzt ist oder nicht
	 * @return Liste mit "leer" wenn in dem Array kein Zug ist, ansonsten "Zug"+GLeisnummer
	 */
	public ArrayList<String> getGleise() {
		ArrayList<String> zuege = new ArrayList<String>();
		for (int i = 0; i < GLEIS_ANZAHL; i++) {
			if (gleise[i] != null) {
				zuege.add("Zug " + i);
			} else {
				zuege.add("leer");
			}
		}
		return zuege;
	}

	/**
	 * Setter für das Gleisarray
	 * @param gleise Array von Zügen
	 */
	public void setGleise(Zug[] gleise) {
		this.gleise = gleise;
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
