package aufgabe22;

import java.util.ArrayList;

public class Flughafen implements Runnable{
	
	ArrayList<Flugzeug> Flugzeuge;
	
	public Flughafen (ArrayList<Flugzeug> list) {
		Flugzeuge = list;
	}
	/**
	 * Es fehlt das die Flugzeit an des Flugzeug gemeldet wird und dass wenn ein Flugzeug geladet ist, ein neues Flugezeug erzeugt wird.
	 */
	@Override
	public void run() {
		int k = Flugzeuge.size();
		while(k>0) {
		System.out.println("^-.._Periode_..-^");
		k = Flugzeuge.size();
		for(int i =0; i<Flugzeuge.size(); i++) {
			if(!Flugzeuge.get(i).isRunning()) {
				Thread flugzeugThread = new Thread(Flugzeuge.get(i));
				flugzeugThread.start();
			}
			System.out.println(Flugzeuge.get(i).toString());
			if(Flugzeuge.get(i).status == FlugStatus.GELANDET) {
				
				
			}
			
			
		}
		
			try {
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}

}
