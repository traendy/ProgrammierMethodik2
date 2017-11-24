package aufgabe3;

public class RangierBahnhof {
	
	private final int GLEIS_ANZAHL = 4;
	private Zug[] gleise;
	
	
	public RangierBahnhof() {
		gleise = new Zug[GLEIS_ANZAHL];
		
	}
	
	
	public void zugEinfahren(Zug zug, int gleisNummer ) {
		if(gleise[gleisNummer]== null) {
			gleise[gleisNummer] = zug;
		}
		
	}
	
	public Zug zugAusfahren(int gleisNummer) {
		if(gleise[gleisNummer]!=null) {
			return gleise[gleisNummer];
		}
		return null;
		
	}
	
	
	public final class Zug{
		
	}

}
