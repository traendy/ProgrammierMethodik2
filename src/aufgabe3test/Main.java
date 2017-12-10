package aufgabe3test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.stage.Stage;

public class Main extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Path path = new Path();
		
		MoveTo moveTo = new MoveTo();
		moveTo.setX(0.0f);
		moveTo.setY(0.0f);
		
		HLineTo hLineTo = new HLineTo();
		hLineTo.setX(70.0f);
		
		QuadCurveTo quadCurveTo = new QuadCurveTo();
		quadCurveTo.setX(120.0f);
		quadCurveTo.setY(60.0f);
		quadCurveTo.setControlX(100.0f);
		quadCurveTo.setControlY(0.0f);
		
		LineTo lineTo = new LineTo();
		lineTo.setX(175.0f);
		lineTo.setY(55.0f);
		
		ArcTo arcTo = new ArcTo();
		arcTo.setX(50.0f);
		arcTo.setY(50.0f);
		arcTo.setRadiusX(50.0f);
		arcTo.setRadiusY(50.0f);
		
		ArcTo arcTo1 = new ArcTo();
		arcTo1.setX(150.0f);
		arcTo1.setY(150.0f);
		arcTo1.setRadiusX(150.0f);
		arcTo1.setRadiusY(150.0f);
		
		path.getElements().add(moveTo);
		path.getElements().add(hLineTo);
		path.getElements().add(quadCurveTo);
		path.getElements().add(lineTo);
		path.getElements().add(arcTo);
		path.getElements().add(arcTo1);
		GridPane torso = new GridPane();
		torso.add(path, 0, 0);
		primaryStage.setScene(new Scene(torso, 300, 200));
		primaryStage.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
