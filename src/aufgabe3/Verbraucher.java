package aufgabe3;

public class Verbraucher<T> extends Thread{

	private IPuffer<T> puffer;
	
	public Verbraucher(IPuffer<T> puffer) {
		this.puffer = puffer;
	}
	
	@Override
	public void run() {
		
	}
}
