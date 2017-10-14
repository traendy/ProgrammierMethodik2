package VL1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogCat {
	private static int exceptionCounter = 0;
	private static List<String> exceptionList = new ArrayList<String>();
	private static final String TAG = LogCat.class.getSimpleName();
	
	public static String e(Exception e, String Tag, String msg) {
		exceptionCounter++;
		StringBuilder sb = new StringBuilder();
		sb.append(e.getClass().getSimpleName()).append(" - ").append(Tag).append(": ").append(msg);
		exceptionList.add(sb.toString());
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static int resetCounter() {
		int temp = exceptionCounter;
		exceptionCounter = 0;
		return temp;
	}
	
	public static int getExceptionCount() {
		return exceptionCounter;
	}

	public static void printExceptionList() {
	StringBuilder sb = new StringBuilder();
		int frameOffset = 6;
		if(!exceptionList.isEmpty()) {
			sb.append("|");
			int longestExceptionCharCount = getCharCountOfLongestStringInList(exceptionList);
			for(int i = 0; i < longestExceptionCharCount+frameOffset; i++) {
				sb.append("=");
			}
			sb.append("|\n");
			for(String s: exceptionList) {
				sb.append("| *** ").append(s).append("\n");
			}
			sb.append("|");
			for(int i = 0; i < longestExceptionCharCount+frameOffset; i++) {
				sb.append("=");
			}
			sb.append("|");
			System.out.println(sb.toString());
		}else {
			System.out.printf("Es wurden %d Exceptions geworfen.\n", exceptionCounter);
		}
		
	}

	private static int getCharCountOfLongestStringInList(List<String> tempList) {
		int count = 0;
		if(tempList.size()>0) {
			for(String s: tempList) {
				if(count < s.length()) {
					count = s.length();
				}
			}
		}
		return count;
	}
	
	public static List<String> resetExceptionList() {
		if(!exceptionList.isEmpty()) {
			List<String> retList = exceptionList;
			exceptionList.clear();
			return retList;
		}else {
			return new ArrayList<String>(null);
		}
	}
	
	public static String writeLogToFile() {
		if(!exceptionList.isEmpty()) {
		String path = "";
		String fileSuffix = ".txt";
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append("LogFile_").append(date.getTime()/60000);
		path = sb.toString();
		File exportFile = new File(path + fileSuffix);
		int fileCounter = 0;
		while(exportFile.exists()) {
			fileCounter++;
			exportFile = new File(path+"_"+fileCounter+fileSuffix);
		}
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(exportFile))){
			for(String s : exceptionList) {
				bufferedWriter.write(s);
				bufferedWriter.write("\n");
			}
			bufferedWriter.write("Es wurden "+exceptionList.size()+" Exceptions geworfen.\n");
			
		} catch (IOException e) {
			LogCat.e(e, TAG, e.getMessage());
		}
		
		return path;
		}else {
			return null;
		}
		
	}
	
	public static void printLog(String TAG, String message, boolean DEBUG, PrintStream output) {
		if(output== null) {
			output = System.out;
		}
		if(DEBUG) {
			output.println(TAG + ": " + message);
		}
	}
	
}
