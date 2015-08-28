package aspera;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Separatewords {
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
		List<String> store = new ArrayList<String>();
		System.out.println(dict.contains("catx"));
		System.out.println(printWords("catxdogcatsrat",store, dict));
		StringBuilder sb = new StringBuilder("catxdogcatsrat");
		printWords2(sb, dict);
		

	}

	private static boolean printWords(String string, List<String> store, Set<String> dictionary) {
		
		if(string.length() == 0) {
			return true;
		}
		for(int i = 1; i <= string.length(); ++i) {
			String orig = string;
			String curWord = string.substring(0, i);
			if((!curWord.equals(orig)) && dictionary.contains(curWord) && printWords(string.substring(i), store, dictionary )) {
				store.add(curWord);
				System.out.println("current word is: "+curWord);
				return true;
			}
		}
		return false;
	}
	

public static void printWords2(StringBuilder sb, Set<String> dict) {

		StringBuilder currentWord = new StringBuilder();
		String orig = sb.toString();
		for (int i = sb.length() - 1; i >= 0; i--) {
			currentWord.insert(0, sb.charAt(i));
			if (dict.contains(currentWord.toString()) && i != 0) {
				sb.insert(i, " ");
				System.out.println("sb: "+sb);				
				currentWord.setLength(0);
			}
		}
		System.out.println(sb);
	}
}