package aufgabe3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * GUI Klasse zeichnet die Simulation und startet diese auch
 * @author speters
 *
 */

public class GUI extends Application {

	// GridPane für den Bahnhof
	private GridPane bahnhofPane = new GridPane();


	/**
	 * Startmethode für die GUI
	 * Startet den Bahnhofs-Task und Zeichnet das GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		RangierBahnhof bahnhof = new RangierBahnhof(bahnhofPane);
		Thread t = new Thread(bahnhof);
		t.start();
		primaryStage.setScene(new Scene(bahnhofPane, 300, 300));
		primaryStage.show();

	}

	/**
	 * Methode um die Application zu starten
	 * @param args ...
	 */
	public void startGui(String[] args) {
		launch(args);

	}

}
