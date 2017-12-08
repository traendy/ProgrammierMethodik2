package aufgabe3;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		RangierBahnhof bahnhof = new RangierBahnhof();
		
		List<Lokfuehrer> lokfuehrere = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			lokfuehrere.add(new Lokfuehrer(bahnhof));
		}
		
		for (Lokfuehrer lokfuehrer : lokfuehrere) {
			lokfuehrer.start();
		}
	}

}
