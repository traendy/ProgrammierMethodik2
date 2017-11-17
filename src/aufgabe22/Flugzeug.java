package aufgabe22;

import java.util.Random;

public class Flugzeug implements Runnable{
	
	private static final long LANDUNG = 1500; 
	private long flugzeit =0;
	Random r = new Random();
	private long CREATED_AT = System.currentTimeMillis();
	public FlugStatus status = FlugStatus.IM_FLUG;
	private boolean running = false;
	private int flugnummer =0;
	
	
	public Flugzeug() {
		this.flugzeit = r.nextInt(10000)+1000;
		this.flugnummer =r.nextInt(1000);
		this.CREATED_AT = System.currentTimeMillis();
	}

	@Override
	public void run() {
		running = true;
		while(running) {
			if((CREATED_AT+flugzeit)-System.currentTimeMillis()<=0) {
				if((CREATED_AT+flugzeit+LANDUNG)-System.currentTimeMillis()<=0) {
					
					status = FlugStatus.GELANDET;
					running = false;
				}else {
					status = FlugStatus.IM_LANDEANFLUG;
				}
			}
		
		}
		
	}

	public boolean isRunning() {
		return running;
	}
	
	public int getFlugnummer() {
		return flugnummer;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Flugnummer: ")
		.append(flugnummer)
		.append(" \tStatus:  ")
		.append(status)
		.append(" \tFlugzeit: ")
		.append(flugzeit/100)
		.append(" \tLebenszeit: ")
		.append((System.currentTimeMillis()-CREATED_AT)/100);
		return sb.toString();
	}

	

}
