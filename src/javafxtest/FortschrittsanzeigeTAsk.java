package javafxtest;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

public class FortschrittsanzeigeTAsk extends Task<Boolean>{
	
	private final Label label;
	
	public FortschrittsanzeigeTAsk(Label label) {
		// TODO Auto-generated constructor stub
		this.label = label;
	}
	
	

	@Override
	protected Boolean call() throws Exception {
		
		int numberOfSteps = 40;
		updateProgress(0, numberOfSteps-1);
		for(int i =0; i< numberOfSteps; i++) {
			Thread.sleep(200);
			updateProgress(i, numberOfSteps-1);
		}
		updateLabel();
		// TODO Auto-generated method stub
		return true;
	}
	
	private void updateLabel() {
		Platform.runLater(()->{
			label.setText("Done");
		});
	}

}
