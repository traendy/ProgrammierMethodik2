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
		//wähle eine von zwei Aufgaben
		
		
		switch(r.nextInt(1)) {
			case 0:
				bahnhof.zugEinfahren(bahnhof.new Zug() , r.nextInt(4));
				break;
			case 1:
				bahnhof.zugAusfahren(r.nextInt(4));
				break;
			default:
				break;
			}
			System.out.println(bahnhof.toString());
		
	}
	
	

}
