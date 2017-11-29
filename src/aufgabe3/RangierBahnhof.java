package aufgabe3;

public class RangierBahnhof {
	
	private final int GLEIS_ANZAHL = 4;
	private Zug[] gleise;
	
	
	public RangierBahnhof() {
		gleise = new Zug[GLEIS_ANZAHL];
		
	}
	
	
	public void zugEinfahren(Zug zug, int gleisNummer ) {
		if(gleise[gleisNummer] == null) {
			gleise[gleisNummer] = zug;
		}
		
		System.out.println("Lokfuehrer mit Zug " + zug.hashCode() + " fahrt in Gleis " + gleisNummer + " ein. \n");
		
	}
	
	public Zug zugAusfahren(int gleisNummer) {
		if(gleise[gleisNummer] != null) {
			Zug ausgefahreneZug = gleise[gleisNummer];
			gleise[gleisNummer] = null;
			System.out.println("Lokfuehrer mit Zug " + ausgefahreneZug.hashCode() + " fahrt in Gleis " + gleisNummer + " aus. \n");
			return ausgefahreneZug;
		}
		return null;
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" _________________________BAHNHOF___________________________\n");
		for (int i = 0; i < GLEIS_ANZAHL; i++) {
			if (gleise[i] == null) {
				sb.append("|-----------------------------------------------------------|\n");
			} else {
				sb.append("|[XXXXXXX][XXXXXX][XXXXXXXXXX][XXXXXX][XXXXXXXX][" + gleise[i].hashCode() + "]|\n");
			}
		}
		sb.append("|___________________________________________________________|\n");
		return sb.toString();
	}
	
	public final class Zug{
		
	}

}