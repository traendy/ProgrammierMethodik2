package innereklassenunderebnisse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mitgliedsklasse {
	public class StandortTracking{
		private List<String> standorte = new ArrayList<>();
		
		public void standorthinzufuegen(String ort) {
			standorte.add(ort);
		}
		
		
		public String toString() {
			return standorte.stream().reduce("", (standort1, standort2) -> standort1 + " " + standort2);
		}
	}
	
	private StandortTracking standortTracking = new StandortTracking();
	
	public void mitgliedWirdBearbeitet(LocalDateTime zeitpunkt, String standort) {
		standortTracking.standorthinzufuegen(standort);
	}
	
	public String toStrin() {
		return "Mitglied: " + standortTracking; 
	}
}
