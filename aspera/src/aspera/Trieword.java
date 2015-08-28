package aspera;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Trieword {
	TreeMap<String, Integer> wordtree;
	ArrayList<String> dict;

	public Trieword() {
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
		//String filename = "C:/Users/Wilson/workspace/aspera/src/aspera/test.txt";
		String filename = "C:/Users/Wilson/workspace/aspera/src/aspera/wordsforproblem.txt";
		readfile(filename);
	}

	public static void readfile(String filename) {
		String line;
		Trieword tree = new Trieword();
		int order = 0;

		try {
			FileReader filereader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			while ((line = bufferedreader.readLine()) != null) {
				tree.addword(line, order++);

			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(tree.findlongestcompoundword());
	}

	class StringLengthComparator implements Comparator<String> {
		public int compare(String arg0, String arg1) {
			return arg0.length() - arg1.length();
		}
	}

}
