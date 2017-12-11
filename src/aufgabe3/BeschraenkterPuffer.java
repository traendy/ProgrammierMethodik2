package aufgabe3;

import java.util.ArrayList;
import java.util.List;

public class BeschraenkterPuffer<T> implements IPuffer<T> {

	  /**
	   * Liste als Speicher
	   */
	  private final List<T> puffer;

	  /**
	   * Aktuelle Anzahl von ELementen im Puffer.
	   */
	  private int anzahlElemente = 0;

	  /**
	   * Konstruktor
	   */
	  public BeschraenkterPuffer(int pufferGroesse) {
	    puffer = new ArrayList<T>();
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
	        return;
	      }
	    }
	    puffer.set(anzahlElemente, objekt);
	    anzahlElemente++;
	    try {
	      Thread.sleep(500);
	    } catch (InterruptedException e) {
	    }
	    System.err.println("---\nNeuer Pufferinhalt: " + this);
	    this.notifyAll();
	  }

	  @Override
	  public synchronized T objektEntnehmen() {
	    while (anzahlElemente == 0) {
	      try {
	        this.wait();
	      } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        return null;
	      }
	    }
	    T objekt = puffer.get(anzahlElemente - 1);
	    anzahlElemente--;
	    try {
	      Thread.sleep(1000);
	    } catch (InterruptedException e) {
	    }
	    System.err.println("---\nNeuer Pufferinhalt: " + this);
	    this.notifyAll();
	    return objekt;
	  }

	  public synchronized int getAnzahlElemente() {
	    return anzahlElemente;
	  }

	  @Override
	  public String toString() {
	    String pufferString = "( ";
	    for (int i = 0; i < anzahlElemente; i++) {
	      pufferString += puffer.get(i) + " ";
	    }
	    pufferString += ")";
	    return pufferString;
	  }
	}