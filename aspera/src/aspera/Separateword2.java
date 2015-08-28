package aspera;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Separateword2 {
	public static void main(String[] args) {
		Set<String> dict= new HashSet<String>();
		dict.add("cats");
		dict.add("catxdogcatsrat");
		dict.add("rat");
		dict.add("hippopotamuses");
		dict.add("cat");
		dict.add("ratcatdogcat");
		dict.add("catsdogcats");
		dict.add("dog");
		dict.add("dogcatsdog");		
		StringBuilder sb = new StringBuilder("dogcatxdogcatsrat");
		printWords2(sb, dict);
	}	

public static void printWords2(StringBuilder sb, Set<String> dict) {
		StringBuilder currentWord = new StringBuilder();
		boolean flag=true;
		String orig = sb.toString();
		for (int i = sb.length() - 1; i >= 0; i--) {
			currentWord.insert(0, sb.charAt(i));
			if (dict.contains(currentWord.toString()) && i != 0) {
				System.out.println("current word: "+currentWord.toString());
				sb.insert(i, " ");							
				currentWord.setLength(0);
			}		
		}
		System.out.println(sb);
	}

}