package javafxtest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	
	private StringProperty vorname = new SimpleStringProperty();
	private StringProperty alias = new SimpleStringProperty();
	private StringProperty nachname = new SimpleStringProperty();
	
	
	public Person(StringProperty vorname, StringProperty alias, StringProperty nachname) {
	
		this.vorname = vorname;
		this.alias = alias;
		this.nachname = nachname;
	}
	public Person(String v, String a, String n) {
		
		this.vorname.set(v); 
		this.alias.set(a); 
		this.nachname.set(n); 
	}

	
	
	public StringProperty vornameProperty() {
		return vorname;
	}
	public void setVorname(StringProperty vorname) {
		this.vorname = vorname;
	}
	public StringProperty aliasProperty() {
		return alias;
	}
	public void setAlias(StringProperty alias) {
		this.alias = alias;
	}
	public StringProperty nachnameProperty() {
		return nachname;
	}
	public void setNachname(StringProperty nachname) {
		this.nachname = nachname;
	}
	
	
	
	public String getVorname() {
		return vorname.get();
	}
	public void setVorname(String vorname) {
		this.vorname.setValue(vorname);
	}
	public StringProperty getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias.set(alias);;
	}
	public StringProperty getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname.set(nachname);;
	}
	
	
	
	
	

}
