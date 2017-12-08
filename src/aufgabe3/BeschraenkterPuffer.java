package aufgabe3;

import java.util.ArrayList;
import java.util.List;

public class BeschraenkterPuffer<T> implements IPuffer<T>
{

	private final List<T> puffer;
	private int anzahlElemente;
	
	public BeschraenkterPuffer(int pufferGroesse) {
		this.puffer = new ArrayList<>();
		anzahlElemente = 0;
		
		for (int i = 0; i < pufferGroesse; i++) {
			puffer.add(null);
		}
	}
	
	@Override
	public synchronized void objektEinfuegen(T objekt) {
		while (anzahlElemente == puffer.size()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		puffer.set(anzahlElemente, objekt);
		anzahlElemente++;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		System.err.println("---\nNeuer Pufferinhalt" + this);
		this.notifyAll();
	}
	
	@Override
	public synchronized T objektEntnehmen() {
	
		return null;
	}
	
	@Override
	public int getAnzahlElemente() {
	
		return anzahlElemente;
	}
}
