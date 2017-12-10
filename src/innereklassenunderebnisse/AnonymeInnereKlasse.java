package innereklassenunderebnisse;

import java.awt.Point;

public class AnonymeInnereKlasse {
	public static void main(String [] args) {
		
		
		Point geradeZahlenPunkt = new Point() {
			@Override
			public void setLocation(int x , int y) {
				super.setLocation((x%2 ==0 ) ? x : x+1, (y%2 ==0)? y : y+1 );
			}
			
			private static final long serialVersionUID = - 5446454564L;
		};
		
		Point normal = new Point();
		normal.setLocation(23, 44);
		geradeZahlenPunkt.setLocation(23, 44);
		System.out.println("normal: " + normal.x + " : " + normal.y);
		System.out.println("normal: " + geradeZahlenPunkt.x + " : " + geradeZahlenPunkt.y);
	}
}
