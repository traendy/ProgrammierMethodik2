package VL1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CounterInFile2 {
private static final String TAG = CounterInFile2.class.getSimpleName();
	
	public static void main(String[] args) {
		LogCat.writeLogToFile();
		String fileName = "test.txt";
		File file = new File(fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("Datei wurde neu erstellt. "+ file.getPath() );
			} catch (IOException e) {
				LogCat.e(e,TAG, e.getMessage());
			}
		}else {
			System.out.println("Datei existiert: " +file.getPath());
		}
		List<String> liste = new ArrayList<String>();
		
		try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
		
		
		String zeile = "";
			while((zeile = bufferedReader.readLine()) != null) {
				liste.add(zeile);
			}
		} catch (IOException e) {
			LogCat.e(e,TAG, e.getMessage());
		}
		if(liste.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("Dateiinahlt:").append("\n");
			for(String s: liste) {
				sb.append(s).append("\n");
				}
			sb.append("Dateiende.");
			
			System.out.println(sb.toString());
		}else {
			System.out.println("Datei war leer.");
		}
		LogCat.printExceptionList();
		System.out.println("Ende.");
		
	}

}
