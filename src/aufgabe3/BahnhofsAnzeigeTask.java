package aufgabe3;

import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.layout.StackPane;

public class BahnhofsAnzeigeTask extends Task<Boolean>{
	
	private ArrayList<StackPane> trainRects;
	
	public BahnhofsAnzeigeTask(ArrayList<StackPane> trainRects) {
		this.trainRects = trainRects;
	}

	@Override
	protected Boolean call() throws Exception {
		boolean running = true;
		while(running) {
			
		}
		return running;
	}

}
