package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		System.out.println("ausdruck bitte: ");
		String suchtext = scanner.nextLine();
		scanner.close();
		String regulaerereAusruck = "ab*";
		
		String[] regexArray = {
				"ab*",
				"[0-6]*",
				"[1-3]{0,1}[1-9]\\.[1]{0,1}[1-9]\\.",
				/*"\\Q([{\\^-$|]})?*+.\\E",*/
				"[abcd]",
				"[^abcd]",
				"[a-du-x]",
				"[a-d[u-x]]",
				"[a-e&&[bc]]",
				"[a-e&&[^bc]]",
				"[a-e&&[^b-d]]",
				"a.\\dx",
				"a*\\d+1x",
				"a*(b{1,2}c?)?d+",
				"^http://",
				"\\b(He)",
				"(\\d\\d)\\1",
				"(\\d\\d)(\\d)\\1\\1\\2",
				"(\\d)+\\1",
				"([A-Za-z]+)\\s([A-Za-z]+)",
				"Haus|Garten",
				
				
		};
		for(String s: regexArray) {
		Pattern pattern = Pattern.compile(s);
		Matcher matcher = pattern.matcher(suchtext);
		if(matcher.matches()) {
			System.out.format("%s passt zu %s \n", suchtext, s);
			if(s.contains("\\s")) {
				System.out.println(matcher.start());
				System.out.println(matcher.group(1));
				System.out.println(matcher.group(2));
				System.out.println(matcher.end());
				System.out.println(matcher.groupCount());
			}
		}else {
			System.out.format("%s passt nicht zu %s \n", suchtext, s);
		}
		
		
//		if(Pattern.matches(s, suchtext)) {
//			System.out.format("%s passt zu %s \n", suchtext, s);
//		}else {
//			System.out.format("%s passt nicht zu %s \n", suchtext, s);
//		}
		
		
		}

	}

}
