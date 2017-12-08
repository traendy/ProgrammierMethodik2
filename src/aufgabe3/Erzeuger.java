package aufgabe3;

import java.util.function.Supplier;

public class Erzeuger<T> extends Thread{

	private IPuffer<T> puffer;
	private final Supplier<T> objektGenerator;
	
	public Erzeuger(IPuffer<T> puffer, Supplier<T> objektGenerator) {
		this.puffer = puffer;
		this.objektGenerator = objektGenerator;
	}
	
	@Override
	public void run() {
		T objekt = objektGenerator.get();
		puffer.objektEinfuegen(objekt);
		System.err.println("Erzeuger hat Objekt "+ objekt + "erzeugt und in den Puffer gelegt.");
	}
}
