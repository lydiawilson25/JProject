package aspera;

import java.util.Set;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LongestCompoundWord {
	private static DataTree wordTree = new DataTree();

	public static void main(String args[]) {

		try {

			String[] sortedWords = null;
			String[] longestWords = null;
			List<String> wordArray = new ArrayList<String>();

			// Use command line to enter file name
			// Reading data from file

			if (args.length == 0) {
				System.out.println("Usage:  java LongestWords filename");
				return;
			}
			byte[] data = new byte[(int) new File(args[0]).length()];

			// Read data from the file into buffer and close the file
			FileInputStream file = new FileInputStream(args[0]);
			file.read(data);
			file.close();

			StringTokenizer tokens = new StringTokenizer(new String(data));

			// Create an array list of tokens returned by StringTokenizer
			// (actually our words)
			while (tokens.hasMoreTokens()) {
				wordArray.add(tokens.nextToken());
			}

			// Print the number of words in the file
			System.out.println("Total number of words in file :   " + wordArray.size());

			// Convert array list to an array of string
			sortedWords = (String[]) wordArray.toArray(new String[wordArray.size()]);
			// Sort the words based on length
			Arrays.sort(sortedWords, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o2.length() - o1.length();
				}
			});

			// Populate trie ADT that we created
			for (String word : sortedWords) {
				// System.out.println("word : " +word);
				wordTree.add(word);
			}
			longestWords = LongestWordsContainingOtherWords(sortedWords);
			// Print the longest word and the number of words made of other
			// words
			System.out.println("Longest Word made of other words:   " + longestWords[0]);
			System.out.println("Second Longest Word made of other words:   " + longestWords[1]);
			System.out.println("Total number of words that can be made of other words :   " + longestWords.length);
			/*
			 * for (String word : contentsArray) { boolean flag =
			 * isCompoundWord(word, true); //System.out.println("flag:"+flag);
			 * if(flag){ System.out.println("something");
			 * compoundedWords.add(word); } }
			 * System.out.println("compoundedWords:"+compoundedWords.toString())
			 * ; Iterator<String> iterator = compoundedWords.iterator();
			 * if(iterator.hasNext()) { System.out.println(
			 * "The Longest word from other words is: " + iterator.next()); }
			 * if(iterator.hasNext()) { System.out.println(
			 * "The Second Longest word from other words is: " +
			 * iterator.next()); }
			 * 
			 * System.out.println("Total number of words: " +
			 * compoundedWords.size());
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * private static boolean isCompoundWord(String word, boolean isStart) {
	 * //System.out.println("word:"+word); int wordLength = word.length();
	 * 
	 * if(isStart) wordTree.remove(word); for (int i = 0; i < wordLength; i++) {
	 * String partialWord = word.substring(0,i+1);
	 * //System.out.println("partialWord:"+partialWord); boolean isAvailable =
	 * wordTree.find(partialWord);
	 * //System.out.println("isAvailable:"+isAvailable); if(isAvailable){
	 * if(i+1==wordLength ||
	 * isCompoundWord(word.substring(i+1,wordLength),false)){ return true; } } }
	 * if(isStart) wordTree.add(word); return false;
	 * 
	 * }
	 */
	public static String[] LongestWordsContainingOtherWords(String[] list) {
		List<String> wordList = new ArrayList<String>();
		for (String word : list) {
			// System.out.println("+++++++++++++++word:"+word);
			// System.out.println(isRequiredWord(word,true));
			if (isRequiredWord(word, true)) {
				wordList.add(word);
			}
		}

		// For debugging
		/*
		 * for(int i=0;i<1;i++) { System.out.println(list[i]);
		 * System.out.println(isRequiredWord(list[i],true));
		 * 
		 * wordList.add(list[i]);
		 * 
		 * 
		 * }
		 */
		return ((String[]) wordList.toArray(new String[wordList.size()]));
	}

	public static boolean isRequiredWord(String word, boolean fullword) {
		// System.out.println("+++++++++++++++isRequiredWord word:"+word);
		// Remove the word so that the word is not matched to itself to find the
		// longest word
		if (fullword) {
			wordTree.remove(word);
		}
		// System.out.println("removed trie:"+trie.toString());
		// Loop over the length of the word
		for (int i = 0; i < word.length(); i++) {
			// System.out.println(word.substring(0, i+1));
			if (wordTree.find(word.substring(0, i + 1))) {
				if (i + 1 == word.length() || isRequiredWord(word.substring(i + 1, word.length()), false)) {
					return true;
				}
			}
		}
		// System.out.println(false);
		if (fullword) {
			wordTree.add(word);
		}
		// System.out.println("added trie:"+trie.toString());
		return false;
	}

	private static String readEntireFile(String filename) throws IOException {
		FileReader in = new FileReader(filename);
		StringBuilder contents = new StringBuilder();
		char[] buffer = new char[4096];
		int read = 0;
		do {
			contents.append(buffer, 0, read);
			read = in.read(buffer);
		} while (read >= 0);
		return contents.toString();
	}

	static class DataTree {
		Node rootNode;

		public DataTree() {
			rootNode = new Node(' ');
		}

		// Function to insert the word in a Trie
		public void add(String word) {
			Node currentNode = rootNode;
			int length = word.length();
			if (length == 0) // For string which is empty
				currentNode.isWordIndicator = true;
			// Loop over the length of string
			for (int i = 0; i < length; i++) {
				Node childNode = currentNode.findChildNode(word.charAt(i));
				// if character already present just increment its prefix
				if (childNode != null) {
					currentNode = childNode;
					currentNode.occurence++;
				} else { // if character not present add a new character node
					currentNode.children.add(new Node(word.charAt(i)));
					currentNode = currentNode.findChildNode(word.charAt(i));
				}
				// Set marker to indicate end of the word
				if (i == length - 1)
					currentNode.isWordIndicator = true;
			}
		}

		public boolean find(String word) {
			Node currentNode = rootNode;
			while (currentNode != null) {
				// Loop over the length of string
				for (int i = 0; i < word.length(); i++) {
					if (currentNode.findChildNode(word.charAt(i)) == null)
						return false;
					else
						currentNode = currentNode.findChildNode(word.charAt(i));
				}

				// It means that string is in the Trie but is it really a word?
				if (currentNode.isWordIndicator == true)
					return true;
				else
					return false;
			}
			return false;
		}

		public boolean remove(String word) {
			if (find(word)) // Check for string is already present, if not it
							// cannot be deleted
			{
				Node currentNode = rootNode;
				Node previous; // To keep track of parent
				int length = word.length();
				while (currentNode != null) {
					// Loop over the length of string
					for (int i = 0; i < length; i++) {
						previous = currentNode;
						// System.out.println(s.charAt(i));
						currentNode = currentNode.findChildNode(word.charAt(i));
						// System.out.println(current.prefix);
						currentNode.occurence--;
						if (currentNode.occurence == 0) {
							// System.out.println(current.content);
							previous.children.remove(currentNode);
							break;
						}
						// System.out.println("current:"+current.toString());
					}
					// remover the endmarker to indicate the word removal
					currentNode.isWordIndicator = false;
					return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			return "DataTree [rootNode=" + rootNode + "]";
		}

		class Node {

			char content;
			LinkedList<Node> children;
			boolean isWordIndicator;
			int occurence;

			public Node(char ch) {
				children = new LinkedList<Node>();
				content = ch;
				occurence = 1;
				isWordIndicator = false;
			}

			public Node findChildNode(char ch) {
				if (children != null) {
					for (Node n : children) {
						if (n.content == ch) {
							return n;
						}
					}
				}
				return null;
			}

			@Override
			public String toString() {
				return "Node [content=" + content + ", isWordIndicator=" + isWordIndicator + ", occurence=" + occurence
						+ ", children=" + children + "]";
			}

		}

	}
}
