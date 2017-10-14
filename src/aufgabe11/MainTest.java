package aufgabe11;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.SAXException;

import VL1.LogCat;
/**
 * TestKlasse für die Senor XML Aufgabe
 * @author speters
 *
 */
public class MainTest {
	private static final String TAG = MainTest.class.getSimpleName();
	
	/**
	 * Testet, ob eine XML Datei eingelesen werden kann und ein Sensor Obejct erstellt wird
	 */
	@Test
	public void getSensorFromXMLFile() {
		String path = "sensortest.xml";
		Sensor sensor= null;
		//Einlesen der gegebenen XML Datei
		
		try {
			sensor = Main.getSensorFromXMLFile(path);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LogCat.e(e, TAG, e.getMessage());
		}
		assertEquals("23.2", String.valueOf(sensor.getMessungen().get(0).getWert()));
		LogCat.printLog(TAG, sensor.toString(), true, null);
		
	}
	
	/**
	 * Testet ob aus einem Sensor eine XML Datei erstllt werden kann.
	 */
	@Test
	public void createSensorXMLFileFromSensorObjectTest() {
		Sensor sensor = new Sensor();
		String path2 = "mySensor.xml";
		boolean exists = false;
		sensor.setiD("Temperatur Kueche");
		
		ArrayList<Messung> list = new ArrayList<>();
		int k = 1;
		for(int i =1; i<123; i++) {
			Messung m = new Messung();
			k *= -1;
			m.setWert((21+i)/i+21*k); 
			m.setZeitstempel(LocalDateTime.now());
			list.add(m);
		}
		sensor.setMessungen(list);
		//sensor XML Datei erstellen
		try {
			exists = Main.createSensorXMLFileFromSensorObject(sensor, path2);
		} catch (TransformerException | ParserConfigurationException e) {
			LogCat.e(e, TAG, e.getMessage());
		}
		assertTrue(exists);
		
		
		try {
			sensor = Main.getSensorFromXMLFile(path2);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LogCat.e(e, TAG, e.getMessage());
		}
		assertEquals("1.0", String.valueOf(sensor.getMessungen().get(0).getWert()));
		LogCat.printLog(TAG, sensor.toString(), true, null);
	}
	
}
