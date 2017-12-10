package innereklassenunderebnisse;

import java.time.LocalDateTime;

import innereklassenunderebnisse.Mitgliedsklasse.StandortTracking;

public class Main {

	public static void main(String[] args) {

		
		Mitgliedsklasse mitgliedsklasse = new Mitgliedsklasse();
		StandortTracking stsa = mitgliedsklasse.new StandortTracking();
		mitgliedsklasse.mitgliedWirdBearbeitet(LocalDateTime.now(), "hiew");
		mitgliedsklasse.mitgliedWirdBearbeitet(LocalDateTime.now(), "da");
		mitgliedsklasse.mitgliedWirdBearbeitet(LocalDateTime.now(), "wo");
		mitgliedsklasse.mitgliedWirdBearbeitet(LocalDateTime.now(), "dort");
		System.out.println(mitgliedsklasse.toStrin());

	}

}
