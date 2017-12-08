package aufgabe3;

public interface IPuffer<T> {

	public void objektEinfuegen(T objekt);
	
	public T objektEntnehmen();
	
	public int getAnzahlElemente();
}
