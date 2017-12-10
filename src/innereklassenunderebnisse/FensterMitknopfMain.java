package innereklassenunderebnisse;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FensterMitknopfMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Fenster mit Knopf.");
		
		GridPane torso = new GridPane();
		// TODO Auto-generated method stub
		FensterMitEventKnopfHandler eventKnopfHandler = new FensterMitEventKnopfHandler();
		ToggleButton knopf = new ToggleButton();
		knopf.setText("SAD");
		knopf.setId("knpf");
		knopf.setOnAction(eventKnopfHandler);
		torso.add(knopf, 1, 1);
		
		ToggleButton gold = new ToggleButton();
		gold.setText("gold");
		gold.setId("gold");
		gold.setOnAction(eventKnopfHandler);
		torso.add(gold, 2, 1);
		
		ToggleButton silver = new ToggleButton();
		silver.setText("silver");
		silver.setId("silver");
		silver.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("silver bitvh");
				
			}
		});
		
		ToggleButton platin = new ToggleButton();
		platin.setText("platin");
		platin.setId("platin");
		platin.setOnAction((event)->{
			System.out.println("platin");
		});
		torso.add(platin, 4, 1);
		torso.add(silver, 3, 1);
		primaryStage.setScene(new Scene(torso, 300, 300));
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
