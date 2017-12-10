package aufgabe3;

public class RangierBahnhof implements Runnable {
	
	private final int GLEIS_ANZAHL =40;
	private Zug[] gleise;
	private int counts =0;
	 private Object monitor1 = new Object();
	  private Object monitor2 = new Object();
	
	public RangierBahnhof() {
		gleise = new Zug[GLEIS_ANZAHL];
		
	}
	
	
	public  void zugEinfahren(Zug zug, int gleisNummer ) {
		synchronized(monitor1){
		if(gleise[gleisNummer]== null) {
			gleise[gleisNummer] = zug;
			System.out.println("Zug wurde auf Gleis: " + gleisNummer + " abgestellt.");
		}
		}
		
	}
	
	public  Zug  zugAusfahren(int gleisNummer) {
		synchronized(monitor1) {
		if(gleise[gleisNummer]!=null) {
			System.out.println("Zug wurde von Gleis: " + gleisNummer + " entfernt.");
			gleise[gleisNummer] = null;
			return gleise[gleisNummer];
		}
		}
		return null;
		
	}
	
	
	public final class Zug{
		
	}


	@Override
	public void run() {
		while(true) {
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

}
