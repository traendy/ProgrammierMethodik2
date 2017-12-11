package aufgabe3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxtest.Person;

public class GUI extends Application implements Observer{
	
	RangierBahnhof bahnhof ;
	Thread thread;
	//thread.start();
	ArrayList<StackPane> trainRects;
	
	GridPane torso = new GridPane();
//	ObservableList<String> zuege;
	Scene scene;


	public GUI(){ 
		//bahnhof = new RangierBahnhof();
		//bahnhof.addObserver(this);
		thread = new Thread(bahnhof);
		trainRects = new  ArrayList<>();
	//	zuege = FXCollections.<String> observableArrayList();
		
	}
	

	@Override
	public void update(Observable o, Object arg) {
		/*
		if (o instanceof RangierBahnhof) {
			//zuege = FXCollections.<String> observableArrayList();
		      System.out.print(((RangierBahnhof) o).toString() + " ");
		      //zuege = FXCollections.<String> observableArrayList();
		      
		      Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					 torso = new GridPane();
				      int i =0;
				      for(String s: ((RangierBahnhof) o).getGleise()) {
				    	  
				    	  	
				    	  	StackPane stackPane = new StackPane();
				    	  	stackPane.getChildren().add(new Text(s));
				    	  	torso.add(stackPane, i, 1);
				    	  	i++;
				      }
				     scene = new Scene(torso);
					
				}
			});
		     

		     
		    }*/
		System.out.println("update");
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		RangierBahnhof bahnhof = new RangierBahnhof(torso);
		Thread t = new Thread(bahnhof);
		t.start();
		primaryStage.setScene(new Scene(torso, 300, 300));
		primaryStage.show();
		
	}
	
	public void startGui(String [] args) {
		//thread.start();
		launch(args);
		
	}
	
}


