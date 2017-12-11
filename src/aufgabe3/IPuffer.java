package aufgabe3;

public interface IPuffer<T> {

	  /**
	   * Einfügen eines Elements in den Puffer.
	   * 
	   * @param objekt
	   *          Objekt, das in den Puffer eingefügt werden soll.
	   */
	  public void objektEinfuegen(T objekt);

	  /**
	   * ZUrückgeben eines Elements aus dem Puffer.
	   * 
	   * @return Objekt, das der Puffer zurückliefert.
	   */
	  public T objektEntnehmen();

	  /**
	   * Liefert die aktuelle Anzahl der Elemente im Puffer.
	   * 
	   * @return Anzahl der Elemente im Puffer.
	   */
	  public int getAnzahlElemente();

	}
