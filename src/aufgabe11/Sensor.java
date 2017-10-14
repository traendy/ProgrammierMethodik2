package aufgabe11;

import java.util.ArrayList;
/**
 * Sensor Klasse mit Settern und Gettern
 * @author speters
 *
 */
public class Sensor {
	private String iD;
	private ArrayList<Messung> messungen;
	
	public Sensor() {
	}
	
	public Sensor(String iD, ArrayList<Messung> messungen) {
	
		this.iD = iD;
		this.messungen = messungen;
	}



	public String getiD() {
		return iD;
	}



	public void setiD(String iD) {
		this.iD = iD;
	}



	public ArrayList<Messung> getMessungen() {
		return messungen;
	}



	public void setMessungen(ArrayList<Messung> messungen) {
		this.messungen = messungen;
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Messung m: messungen) {
			sb.append("\t").append(m.toString()).append("\n");
		}
		return "Sensor [iD=" + iD + ", \n"+ sb.toString() + "]";
	}
	
	
	
	
}
