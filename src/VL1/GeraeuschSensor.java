package VL1;

import java.io.IOException;

public class GeraeuschSensor implements AutoCloseable {
	
	public GeraeuschSensor() throws IOException{
		
	}
	
	public void verbindungBeenden() throws IOException{
		
	}
	
	public int getWert() {
		return 9000;
	}

	@Override
	public void close() throws IOException {
		 verbindungBeenden();
		
	}
	
	

}
