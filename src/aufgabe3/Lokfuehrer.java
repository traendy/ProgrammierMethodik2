package aufgabe3;

import java.util.Random;


public class Lokfuehrer extends Thread{
	private RangierBahnhof bahnhof;
	private Random r = new Random();
	
	public Lokfuehrer (RangierBahnhof bahnhof) {
		this.bahnhof = bahnhof;
	}

	@Override
	public void run() {		
		
		switch(r.nextInt(2)) {
			case 0:
				bahnhof.zugEinfahren(bahnhof.new Zug() , r.nextInt(4));
				break;
			case 1:
				bahnhof.zugAusfahren(r.nextInt(4));
				break;
			default:
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
	
	

}
