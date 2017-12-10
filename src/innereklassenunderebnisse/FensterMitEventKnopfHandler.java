package innereklassenunderebnisse;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

public class FensterMitEventKnopfHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Object source = event.getSource();
		if(source instanceof ToggleButton) {
			ToggleButton butt = (ToggleButton) source;
			switch(butt.getId()){
			case "gold":
				System.out.println("gold");
				break;
			default:
				System.out.println("not Gold the other one");
				break;
			}
		}

		
	}
	
	
	

}
