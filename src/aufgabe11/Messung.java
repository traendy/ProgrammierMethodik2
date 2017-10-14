package aufgabe11;

import java.time.LocalDateTime;

public class Messung {
	/**
	 * Messung Klasse für dem Sensor mit Settern und Gettern
	 */
	private double wert;
	private LocalDateTime zeitstempel;
	
	public Messung() {
		
	}

	
	public Messung(double wert, LocalDateTime zeitstempel) {
		this.wert = wert;
		this.zeitstempel = zeitstempel;
	}

	public double getWert() {
		return wert;
	}

	public void setWert(double wert) {
		this.wert = wert;
	}

	public LocalDateTime getZeitstempel() {
		return zeitstempel;
	}

	public void setZeitstempel(LocalDateTime zeitstempel) {
		this.zeitstempel = zeitstempel;
	}

	@Override
	public String toString() {
		return "Messung [wert=" + wert + ", zeitstempel=" + zeitstempel + "]";
	}
	
	
	
	

}
