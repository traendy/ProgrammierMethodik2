package aufgabe3;

public class Main {

	public static void main(String[] args) {

		RangierBahnhof bahnhof = new RangierBahnhof();
		Lokfuehrer lokfuehrer = new Lokfuehrer(bahnhof);
		
		
		Thread t = new Thread(lokfuehrer);
		t.run();
	}

}
