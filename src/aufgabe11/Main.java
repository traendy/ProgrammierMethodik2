package aufgabe11;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import VL1.LogCat;
/**
 * Klasse zur Aufgabe 1.1 XML
 * beinhaltet eine main Methode, die Sensordaten erstellt und aus einer XML Datei einliest und in eine
 * andere Schreibt.
 * @author speters
 *
 */

public class Main {
	/*
	 * Hilfs Konstanten zum Debuggen
	 */
	private static final String TAG = Main.class.getSimpleName();
	private static final boolean DEBUGING = false;
	
	/**
	 * Main Methode siehe Klassenbeschreibung.
	 * @param args Pfad zu einer Sensor XML Datei, die ausgelesen werden soll
	 */
	public static void main(String [] args) {
		String path = "sensortest.xml";
		if(!args.equals("")) {
			path = args[0];
		}
		
		String path2 = "mySensor.xml";
		Sensor sensor= null;
		//Einlesen der gegebenen XML Datei
		try {
			sensor = getSensorFromXMLFile(path);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LogCat.e(e, TAG, e.getMessage());
		}
		LogCat.printLog(TAG, sensor.toString(), true, null);
		//Sensor erstellen aus dem die XML Datei erstellt werden soll
		sensor = new Sensor();
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
			createSensorXMLFileFromSensorObject(sensor, path2);
		} catch (TransformerException | ParserConfigurationException e) {
			LogCat.e(e, TAG, e.getMessage());
		}
		
		//testen ob die erstelle Sensor XML datei auch einlebar ist.
		try {
			sensor = getSensorFromXMLFile(path2);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LogCat.e(e, TAG, e.getMessage());
		}
		LogCat.printLog(TAG, sensor.toString(), true, null);
		LogCat.printExceptionList();
	}
	
	/**
	 * Erstellt eine XML Datei, die einem Sensor Object entspricht.
	 * @param sensor Sensor, der als XML Datei geführt werden soll
	 * @param fileName Dateiname/Pfad der zu erstellen XML Datei
	 * @return gibt true zurück wenn die Datei erstellt wurde
	 * @throws TransformerException wird geworfen, wenn der Transformer einen Fehler hat
	 * @throws ParserConfigurationException wird geworfen wenn der Parser einen Fehler hat
	 */
	public static boolean createSensorXMLFileFromSensorObject(Sensor sensor, String fileName) throws TransformerException, ParserConfigurationException {
		String sensorString = "Sensor";
		String id= "id";
		String messung = "Messung";
		String wert = "wert";
		String zeitStempel = "zeitstempel";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// Wurzel (Sensor)
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(sensorString);
		doc.appendChild(rootElement);
		
		// Attribute des Senors
		Attr idAttr = doc.createAttribute(id);
		idAttr.setValue(sensor.getiD());
		rootElement.setAttributeNode(idAttr);
		
		/*
		 * Durchghen der Liste des Senors und Erstellung der Messungen mit ihren Attributen
		 */
		for(Messung m : sensor.getMessungen()) {
			// Child Element Messung
			Element messungElement = doc.createElement(messung);
			rootElement.appendChild(messungElement);

			// Attribute der Messung.
			Attr wertAttr = doc.createAttribute(wert);
			wertAttr.setValue(String.valueOf(m.getWert()));
			messungElement.setAttributeNode(wertAttr);
			
			Attr zeitAttr = doc.createAttribute(zeitStempel);
			zeitAttr.setValue(String.valueOf(m.getZeitstempel()));
			messungElement.setAttributeNode(zeitAttr);
		}
		
		/*
		 * Schreiben der neuen XML Datei an den angegebenen Pfad
		 */
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(fileName));
		transformer.transform(source, result);

		// Debug und Ausgabe
		File f = new File(fileName);
		LogCat.printLog(TAG, "File saved: "+ f.exists(), DEBUGING, null);
		return f.exists();

	}
	
	/**
	 * Lesen einer Sensor XML Datei und erstellen eines Sensor Objects aus dem gelesenen
	 * @param path Pfad zur XML Datei
	 * @return gelesenes Sensor Object
	 * @throws ParserConfigurationException Exception wenn der Parser einen Fehler hat
	 * @throws SAXException wird geworfen wenn der SAX einen Fehler hat
	 * @throws IOException wird geworen wenn die Datei nicht gelesen werden kann
	 */
	public static Sensor getSensorFromXMLFile(String path) throws ParserConfigurationException, SAXException, IOException {
		Sensor sensor = new Sensor();
		ArrayList<Messung> messungen = new ArrayList<Messung>();
		
		//Initialisieren des Builders
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		
		//Parsen der XNL Datei
		Document document = builder.parse(new File(path));
		
		//Holen des Wurzel Elements
		Element element = document.getDocumentElement();
		NamedNodeMap attribute = element.getAttributes(); 
		
		LogCat.printLog(TAG, element.getNodeName(), DEBUGING, null);
		
		// Holen aller Attribute des Wurzelelements
		for (int i = 0; i < attribute.getLength(); i++) {
			Node attribut = attribute.item(i);
			LogCat.printLog(TAG, attribut.getNodeName() + ": " + attribut.getNodeValue(), DEBUGING, null);
			
			sensor.setiD(attribut.getNodeValue());
			}
		
		// Holen aller seiner Kinder (Messungen)
		NodeList nodeList = element.getChildNodes();
		
		for(int k = 0; k<nodeList.getLength(); k++) {
		
			Node node = nodeList.item(k);
				//holen der Attribute der Kinder (Messungen)
				if(node.hasAttributes()) {
					Messung m = new Messung();
					LogCat.printLog(TAG, node.getNodeName() + ": ", DEBUGING, null);
					
					
					NamedNodeMap attributeMap = node.getAttributes();
					for (int i = 0; i < attributeMap.getLength(); i++) {
						Node attribut = attributeMap.item(i);
						LogCat.printLog(TAG, attribut.getNodeName() + ": " + attribut.getNodeValue(), DEBUGING, null);
					
						
						if(attribut.getNodeName().trim().equals("wert")) {
							m.setWert(Double.parseDouble(attribut.getNodeValue()));
						}
						if(attribut.getNodeName().trim().equals("zeitstempel")) {
							m.setZeitstempel(LocalDateTime.parse(attribut.getNodeValue()));
						}
						}
					messungen.add(m);
				}
				
				
		}
		//setzen der Messungen im Seonsor aObject und Rückgabe
		sensor.setMessungen(messungen);
		return sensor;
		}
}
