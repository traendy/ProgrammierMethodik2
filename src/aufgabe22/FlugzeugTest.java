package aufgabe22;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlugzeugTest {

	@Test
	public void updateAirportTimeTest() {
		Flugzeug f = new Flugzeug(1);
		f.updateAirportTime(0);
		System.out.println(f.getStatus());
		assertEquals(FlugStatus.IM_FLUG, f.getStatus());
		f.updateAirportTime(200000);
		
		assertEquals(FlugStatus.GELANDET, f.getStatus());
	}

}
