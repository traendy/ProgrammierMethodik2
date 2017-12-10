package javafxtest;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class RechnungsWertVeraenderung implements ChangeListener<Number>{

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		System.out.println("Veränderung: " + oldValue + " -> " + newValue);
		
	}
	
}
