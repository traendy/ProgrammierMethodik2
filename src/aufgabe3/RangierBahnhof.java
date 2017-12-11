package aufgabe3;

import java.util.ArrayList;



import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class RangierBahnhof extends Task<Object> {

	public static final int GLEIS_ANZAHL = 10;
	private Zug[] gleise;
	private int counts = 0;
	private static Object monitor1 = new Object();
	private String s = "";
	
	private final GridPane grid;

	public RangierBahnhof(GridPane grid) {
		gleise = new Zug[GLEIS_ANZAHL];
		this.grid = grid;
	}

	public void zugEinfahren(Zug zug, int gleisNummer, long id) {
		
		synchronized (monitor1) {
			boolean done = false;
			while (!done) {
				if (gleise[gleisNummer] == null) {
					done = true;
					gleise[gleisNummer] = zug;
					
					s = "Zug wurde auf Gleis: " + gleisNummer + " durch " + id +" abgestellt.";
					System.out.println(s);
					
				
					updateGridPane(-1);
					monitor1.notify();
					return;
				} else {
					done = false;
					try {
						monitor1.wait();
					} catch (InterruptedException e) {
						return;
					}
				}
			}
			//this.getClass().notify();
		}
		
	}

	public Zug zugAusfahren(int gleisNummer, long id) {
		
		synchronized (monitor1) {
			boolean done = false;
			while (!done) {
				if (gleise[gleisNummer] != null) {
					done = true;
					s = "Zug wurde von Gleis: " + gleisNummer + " durch " + id +" entfernt.";
					
					System.out.println(s);
					gleise[gleisNummer] = null;
					
					updateGridPane(gleisNummer);
					monitor1.notify();
					return gleise[gleisNummer];
				} else {
					done = false;
					try {
						monitor1.wait();
					} catch (InterruptedException e) {
						return null;
					}
				}
			}
			//this.getClass().notify();

		}
		
		return gleise[gleisNummer];

	}

	public final class Zug {

	}

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


	private void updateGridPane(int gleisnummer) {
		
		System.out.println("update Grid");
		Platform.runLater(()->{
		
	      int i =0;
	      
	      if(gleisnummer >0) {
	    	  StackPane stackPane = new StackPane();
	    	  	Rectangle r = new Rectangle();
	    	  	r.setHeight(30);
	    	  	r.setWidth(60);
	    	  	r.setFill(Color.DARKSEAGREEN);
	    	  	
	    	  	
	    	  	stackPane.getChildren().addAll(r, new Label("Zug " + gleisnummer));
	    	  	grid.add(stackPane, 3, 1);
	    	  
	      }
	      
	      
	      for(String s: getGleise()) {
	    	  	
	    	  	
	    	  	StackPane stackPane = new StackPane();
	    	  	Rectangle r = new Rectangle();
	    	  	r.setHeight(30);
	    	  	r.setWidth(60);
	    	  	if(s.equals("leer")) {
	    	  		r.setFill(Color.GAINSBORO);
	    	  	}else {
	    	  		r.setFill(Color.BURLYWOOD);
	    	  	}
	    	  	
	    	  	stackPane.getChildren().addAll(r, new Label(s));
	    	  	grid.add(stackPane, 1, i);
	    	  	i++;
	      }
		});
	
	}

	public String toString() {
		return s;
	}

	public ArrayList<String> getGleise() {
		ArrayList<String> zuege = new ArrayList<String>();
		for(int i =0; i<GLEIS_ANZAHL; i++) {
			if(gleise[i]!= null) {
				zuege.add("Zug "+i);
			}else {
				zuege.add("leer");
			}
		}
		return zuege;
	}

	public void setGleise(Zug[] gleise) {
		this.gleise = gleise;
	}

	
	
	
}

	
	


