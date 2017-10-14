package VL1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CounterinFile {

	private static final String TAG = CounterinFile.class.getSimpleName();
	
	public static void main(String[] args) {
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
		
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			LogCat.e(e,TAG, e.getMessage());
		}
		
		List<String> liste = new ArrayList<String>();
		String zeile = "";
		try {
			while((zeile = bufferedReader.readLine()) != null) {
				liste.add(zeile);
			}
		} catch (IOException e) {
				if(bufferedReader != null)
					try {
						bufferedReader.close();
					} catch (IOException e1) {
						LogCat.e(e1,TAG, e1.getMessage());
					}
			LogCat.e(e,TAG, e.getMessage());
		}
		try {
			bufferedReader.close();
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
