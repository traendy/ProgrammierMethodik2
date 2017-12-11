package aufgabe3;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * GUI Klasse zeichnet die Simulation und startet diese auch
 * 
 * @author speters
 *
 */

public class GUI extends Application implements Observer {

	// GridPane für den Bahnhof
	private GridPane grid = new GridPane();

	/**
	 * Startmethode für die GUI Startet den Bahnhofs-Task und Zeichnet das GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		RangierBahnhof bahnhof = new RangierBahnhof();
		bahnhof.addObserver(this);
		Thread t = new Thread(bahnhof);
		t.start();
		primaryStage.setScene(new Scene(grid, 300, 300));
		primaryStage.show();

	}

	/**
	 * Methode um die Application zu starten
	 * 
	 * @param args
	 *            ...
	 */
	public void startGui(String[] args) {
		launch(args);

	}

	/**
	 * Updated das Gridpane, das den Bahnhof darstellt
	 */
	@SuppressWarnings("restriction")
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof RangierBahnhof)
			System.out.println("update Grid");
		Platform.runLater(() -> {
			int gleisnummer = ((RangierBahnhof) o).getGleisNummer();
			int i = 0;
			// Zug wurde ausgefahren und wird auf das Gleis gestellt
			if (gleisnummer > 0) {
				StackPane stackPane = new StackPane();
				Rectangle r = new Rectangle();
				r.setHeight(30);
				r.setWidth(60);
				r.setFill(Color.DARKSEAGREEN);

				stackPane.getChildren().addAll(r, new Label("Zug " + gleisnummer));
				grid.add(stackPane, 3, 1);

			}
			// NeuZeichnen aller eingefahrenen ZÃ¼ge
			for (String s : ((RangierBahnhof) o).getGleise()) {

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
}
