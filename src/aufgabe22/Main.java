package aufgabe22;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		ArrayList<Flugzeug> FlugzeugListe = new ArrayList<>();
		for(int i = 0; i<5; i++) {
			FlugzeugListe.add(new Flugzeug());
		}
		Flughafen flughafen = new Flughafen(FlugzeugListe);
		Thread t = new Thread(flughafen);
		t.start();
	}

}
