package VL1;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try(GeraeuschSensor sensor = new GeraeuschSensor();){
			System.out.println(sensor.getWert());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
