package string;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexdictionary{

	public static void main(String[] args) {
		HashSet<String> dict = new HashSet<String>();
		dict.add("go");
		dict.add("goal");
		dict.add("goals");
		dict.add("special");

		StringBuilder sb = new StringBuilder();

		for (String s : dict) {
			sb.append(s + "|");
		}

		String pattern = sb.toString().substring(0, sb.length() - 1);
		pattern = "(" + pattern + ")*";
		Pattern p = Pattern.compile(pattern);
		System.out.println("pattern: "+pattern);
		System.out.println("sb: "+sb);
		Matcher m = p.matcher("goalspecial");

		if (m.matches()) {
			System.out.println("match");
		}
	}
}