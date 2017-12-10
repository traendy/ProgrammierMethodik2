package aufgabe3;

public class Main {

	public static void main(String[] args) {
		RangierBahnhof bahnhof = new RangierBahnhof();
		Thread thread = new Thread(bahnhof);
		thread.start();

	}

}
