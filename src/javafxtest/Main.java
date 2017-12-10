package javafxtest;

import java.awt.Checkbox;
import java.awt.TextField;
import java.util.Observable;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main  extends Application{
	
	
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Fenster mit Knopf.");
		
		RechnungsWertVeraenderung veraenderung = new RechnungsWertVeraenderung();
		Rechnung rechnung = new Rechnung(veraenderung);
		rechnung.setBetrag(346.23);
		ToggleButton kopf = new ToggleButton();
		kopf.setText("kopf");
		ToggleButton kopf1 = new ToggleButton();
		kopf1.setText("1");
		ToggleButton kopf2 = new ToggleButton();
		kopf2.setText("2");
		ToggleButton kopf3 = new ToggleButton();
		kopf3.setText("3");
		ToggleButton kopf4 = new ToggleButton();
		kopf4.setText("4");
		ToggleButton kopf5 = new ToggleButton();
		kopf5.setText("5");
		GridPane torso = new GridPane();
		torso.add(kopf,0,0);
		torso.add(kopf1,1,2);
		torso.add(kopf2,21,1);
		torso.add(kopf3,1,1);
		torso.add(kopf4,3,2);
		
		ComboBox<String> comboBox = new ComboBox<>( FXCollections.observableArrayList("top", "bo", "asj"));
		comboBox.setValue("jo");
		
		CheckBox box = new CheckBox("checker");
		box.setSelected(true);
		
		TextField tf = new TextField("sdu");
		TextArea ta = new TextArea("5");
		rechnung.setBetrag(Double.valueOf((ta.getText())));
		torso.add(ta, 5, 5);
		
		torso.add(box, 4, 4);
		
		torso.add(comboBox,3, 3);
		
		// Personenne Tabelle
		ObservableList<Person> personen = FXCollections.<Person> observableArrayList();
		Person p1 = new Person("fasf", "sdfgs", "fdsf");
		personen.add(p1);
		personen.add(new Person("Professor X", "Charles", "Xavier"));
		personen.add(new Person("Wolverine", "James", "Howlett"));
		personen.add(new Person("Cyclops", "Scott", "Summers"));
		
		TableView<Person> tabellenAnsicht = new TableView<>();
		
		TableColumn<Person, String> aliasSpalte = new TableColumn<>("Alias");
		aliasSpalte.setCellValueFactory(new PropertyValueFactory<Person, String>("alias"));
		TableColumn<Person, String> vornameSpalte = new TableColumn<>("Vorname");
		aliasSpalte.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
		TableColumn<Person, String> nachnameSpalte = new TableColumn<>("Nachname");
		aliasSpalte.setCellValueFactory(new PropertyValueFactory<Person, String>("nachname"));
		
		tabellenAnsicht.getColumns().add(aliasSpalte);
		tabellenAnsicht.getColumns().add(vornameSpalte);
		tabellenAnsicht.getColumns().add(nachnameSpalte);
		
		
		tabellenAnsicht.setItems(personen);
		
		// Progressbar:
		
		ProgressBar fortschrittsAnzeige = new ProgressBar();
		torso.add(fortschrittsAnzeige, 2, 2);
		Label label = new Label("in Arbeit....");
		torso.add(label, 3, 2);
		
		FortschrittsanzeigeTAsk task  = new FortschrittsanzeigeTAsk(label);
		
		fortschrittsAnzeige.progressProperty().unbind();
		fortschrittsAnzeige.progressProperty().bind(task.progressProperty());
		
		Thread worker = new Thread(task);
		worker.start();
		
		torso.add(tabellenAnsicht, 8, 8);

		primaryStage.setScene(new Scene(torso, 300, 200));
		primaryStage.show();
	}
	
	
	
	public static void main (String [] args) {
		launch(args);
	}

}
