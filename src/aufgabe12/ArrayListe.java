package aufgabe12;
/**
 * Eigne ArrayListe mit allen vorgegbenen Methoden
 * @author speters
 *
 * @param <T> Parameter der Liste
 */
public class ArrayListe<T extends Comparable<T>>{
	//HilfsConstanten
	private static final String TAG = ArrayListe.class.getSimpleName();
	
	//Variablen 
	private int anzahlElemente;
	private Object[] elemente;
	private boolean debug = false;
	
	/**
	 * Constructor
	 */
	public ArrayListe() {
		elemente = new Object[0];
		anzahlElemente =0;
	}
	
	/**
	 * Hinzufügen eines Objectes zur Liste
	 * @param object hinzuzufügendes Object
	 */
	public void hinzufeuegen(T object) {
		if(anzahlElemente == elemente.length) {
			//array vergrößern
			int newLength = elemente.length + elemente.length/2 +1;
			Object[] tempArray = new Object[newLength];
		
			System.arraycopy(elemente, 0, tempArray, 0, elemente.length);
			elemente = tempArray;
			
		}
		//hinzfügen
		
		elemente[anzahlElemente] = object;
		anzahlElemente++;
	}
	
	
	/**
	 * Get Object an Postition
	 * @param pos POsition in Liste
	 * @return Object an Position pos
	 */
	@SuppressWarnings("unchecked")
	public T get(int pos) {
		if(pos<anzahlElemente && pos >=0) {
			return (T) elemente[pos];
		}
		
		else {
			return null;
		}
	}
	
	/**
	 * Entferne Object in der Liste. Entfernt
	 * @param object zu entfernendes Object
	 */
	public void entferne(T object) {
		int deletedPosition = -1;
		for(int i = 0; i<elemente.length && elemente[i] != null; i++) {
			if(elemente[i]== object) {
				deletedPosition = i;
			}
		}
		if(deletedPosition<0) {
			System.out.println("ELement nicht gefunden.");
			return;
		}
		entferneElementAnIndex(deletedPosition);
		
	}
	
	/**
	 * Entfernt Element an Index pos in Liste
	 * @param pos position in Liste des zu entfernenden Objects
	 */
	public void entferneElementAnIndex(int pos) {
		if(pos>elemente.length || pos<0) {
			System.out.println("Index out of Bounds");
			return;
		}
		if(elemente[pos]== null) {
			return;
		}
		System.arraycopy(elemente, pos+1, elemente, pos, elemente.length-pos-1);
		anzahlElemente--;
		
	}
	
	/**
	 * gibt die Anzahl der ELemente in der Liste zurück
	 * @return Anzahl der ELemente in der Liste
	 */
	public int getAnzahlElemente() {
		return anzahlElemente;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Object o: elemente) {
			if(o != null) {
				sb.append(o.toString()).append(", ");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * gibt das kleinse Element zurück
	 * @return
	 */
	public <T extends Comparable<T>>T getKleinstesElement() {
		Comparable object = (Comparable) elemente[0];
		for(int i =0; i<elemente.length; i++) {
			Comparable object2 = (Comparable) elemente[i];
			if(object2 != null) {
			if(object2.compareTo(object)<0) {
				object = object2;
			}
			}
		}
		
		return (T) object;
	}
	
	
	/**
	 * Setzt die Klasse in den Dbug Modus
	 * @param debug
	 */
	public void isDebugging(boolean debug) {
		this.debug = debug;
	}
	

	


}
