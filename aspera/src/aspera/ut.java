package aspera;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class ut {
	TreeMap<String, Integer> wordtree;
	ArrayList<String> dict;

	public ut() {
		wordtree = new TreeMap<String, Integer>(new StringLengthComparator());
		dict = new ArrayList<String>();
	}

	public void addword(String word, Integer order) {
		wordtree.put(word, order);
		dict.add(word);
	}

	public String findlongestcompoundword() {
		while (wordtree.size() > 0) {
			String word = wordtree.lastKey();
			wordtree.remove(word);
			dict.remove(word);
			if (iscompoundword(word))
				return word;
		}
		return "";
	}

	public boolean iscompoundword(String word) {
		if (dict.contains(word))
			return true;
		for (int i = 0; i < word.length(); i++) {
			String prefix = word.substring(0, i);
			if (iscompoundword(prefix)) {
				String remainder = word.substring(i, word.length());
				if (remainder.length() == 0)
					return true;
				return iscompoundword(remainder);
			}

		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ut cwf = new ut();
		int order = 0;
		cwf.addword("cats", order++);
		cwf.addword("catxdogcatsrat", order++);
		cwf.addword("rat", order++);
		cwf.addword("hippopotamuses", order++);
		cwf.addword("cat", order++);
		cwf.addword("ratcatdogcat", order++);
		cwf.addword("catsdogcats", order++);
		cwf.addword("dog", order++);
		cwf.addword("dogcatsdog", order++);
		System.out.println(cwf.findlongestcompoundword());

	}

	class StringLengthComparator implements Comparator<String> {
		public int compare(String arg0, String arg1) {
			return arg0.length() - arg1.length();
		}
	}

}
