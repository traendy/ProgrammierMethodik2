package javafxtest;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Rechnung {
	
	
	public Rechnung (ChangeListener<Number> amountVeraenderungsListener) {
		betrag.addListener(amountVeraenderungsListener);
	}
	
	
	
	private DoubleProperty betrag = new SimpleDoubleProperty();

	public final Double getBetrag() {
		return betrag.get();
	}

	public final void setBetrag(Double value) {
		this.betrag.set(value);;
	}
	
	public DoubleProperty betragProperty() {
		return betrag; 
	}
	

}
