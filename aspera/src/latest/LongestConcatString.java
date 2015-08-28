package latest;

import java.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The LongestConcatString program implements an application that accepts a
 * filename as argument and displays the following to the standard output. 1.The
 * Longest word formed by concatenation of other words. 2. The Second longest
 * word 3. Total Number of words formed by the concatenation of other words
 * 
 * @author Lydia Chandrakanthan
 * @since 2015-08-19
 */

public class LongestConcatString {
	private static DataTree wordsTree = new DataTree();

	public static void main(String args[]) throws ProgramException {

		String[] sortedWordArray = null;
		List<String> collectionWords = new ArrayList<String>();
		String fileName = null;
		;
		int noOfWords = 2; // To display two longest words made from other words.
		try {

			if (args.length !=1) {
				System.out.println("Please provide the file name.");
				return;
			} else {
				fileName = args[0];
			}
			//The input file is stored in a string array
			sortedWordArray = readInput(fileName.trim());
			//The words are stored in a data structure for easy retrieval
			for (String s : sortedWordArray) {
				wordsTree.add(s);
			}
			//Each word is checked if it is made of other words
			for (String s : sortedWordArray) {
				boolean flag = isCollectiveWord(s, true);
				if (flag)
					collectionWords.add(s);
			}
			//The valid words are stored into the ArrayList collectionWords
			Iterator<String> iterator = collectionWords.iterator();
			for (int i = 1; i <= noOfWords; i++) {
				System.out.println(i + ") " + "#" +i + " longest word from other words is: " + iterator.next());
			}

			System.out.println("3) Total number of words that can be constructed of other words in the list " + collectionWords.size());

		} catch (Exception e) {
			throw new ProgramException("Program failed to execute");
		}

	}

	/**
	 * This method is used to find if the given word is made of the other words
	 * 
	 * @param word
	 *            The current word to check if it is made of the other words
	 * @param isStart
	 *            If this is the original word then remove it from the data structure.
	 * @return boolean This returns boolean based on if the word is
	 *         concatenation or not.
	 */

	public static boolean isCollectiveWord(String word, boolean isStart) {
		int wordLength = word.length();
		if (isStart)
			wordsTree.remove(word);
		
		//Check if the word is made of other words
		for (int i = 0; i < wordLength; i++) {
			String partialWord = word.substring(0, i + 1);
			boolean isAvailable = wordsTree.find(partialWord);
			if (isAvailable) {
				if (i + 1 == wordLength || isCollectiveWord(word.substring(i + 1, word.length()), false))
					return true;
			}
		}
		if (isStart)
			wordsTree.add(word);
		
		return false;
	}

	/**
	 * This method is used to read the input from a file.
	 * 
	 * @param filename
	 *            The name of the file which contains the list of the words.
	 * @return String[] This returns a string array containing the list of the
	 *         words.
	 */

	private static String[] readInput(String fileName) throws ProgramException {
		String[] orderedWords;
		try {
			List<String> wordsList = new ArrayList<String>();

			byte[] data = new byte[(int) new File(fileName).length()];

			FileInputStream file = null;
			try {
				file = new FileInputStream(fileName);
				file.read(data);
			} catch (IOException e) {
				throw new ProgramException("Failed to read the file.");
			} finally {
				try {
					file.close();
				} catch (IOException e) {
					throw new ProgramException("Failed to close the file.");
				}
			}

			StringTokenizer tokens = new StringTokenizer(new String(data));

			while (tokens.hasMoreTokens()) {
				wordsList.add(tokens.nextToken());
			}

			orderedWords = (String[]) wordsList.toArray(new String[wordsList.size()]);

			Arrays.sort(orderedWords, new Comparator<String>() {
				//Override the compare method to sort the words according to the length.
				@Override
				public int compare(String o1, String o2) {
					return o2.length() - o1.length();
				}
			});
		} catch (Exception e) {
			throw new ProgramException("Failed to sort the User input");
		}
		return orderedWords;
	}

	/**
	 * This class DataTree contains a list of nodes.
	 * 
	 * @param rootNode
	 *            The root of all other nodes.
	 * 
	 */

	static class DataTree {
		Node rootNode;

		public DataTree() {
			rootNode = new Node(' ');
		}

		/**
		 * This method is used to add a particular word.
		 * 
		 * @param word
		 *            The word to be added.
		 * @return boolean Returns true if the word has been added.
		 * 
		 */

		public void add(String word) {
			Node currentNode = rootNode;
			int length = word.length();
			if (length == 0)
				currentNode.isWordIndicator = true;

			for (int i = 0; i < length; i++) {
				Node childNode = currentNode.findChildNode(word.charAt(i));

				if (childNode != null) {
					currentNode = childNode;
					currentNode.occurence++;
				} else {
					currentNode.children.add(new Node(word.charAt(i)));
					currentNode = currentNode.findChildNode(word.charAt(i));
				}

				if (i == length - 1)
					currentNode.isWordIndicator = true;
			}
		}

		/**
		 * This method is used to find a particular word.
		 * 
		 * @param word
		 *            The word to be searched.
		 * @return boolean Returns true if the word has been found.
		 * 
		 */

		public boolean find(String word) {
			Node currentNode = rootNode;
			while (currentNode != null) {

				for (int i = 0; i < word.length(); i++) {
					if (currentNode.findChildNode(word.charAt(i)) == null)
						return false;
					else
						currentNode = currentNode.findChildNode(word.charAt(i));
				}

				if (currentNode.isWordIndicator == true)
					return true;
				else
					return false;
			}
			return false;
		}

		/**
		 * This method is used to remove a particular word.
		 * 
		 * @param word
		 *            The word to be removed.
		 * @return boolean Returns true if the word has been removed.
		 * 
		 */

		public boolean remove(String word) {
			if (find(word))

			{
				Node currentNode = rootNode;
				Node previous;
				int length = word.length();
				while (currentNode != null) {

					for (int i = 0; i < length; i++) {
						previous = currentNode;
						currentNode = currentNode.findChildNode(word.charAt(i));
						currentNode.occurence--;
						if (currentNode.occurence == 0) {
							previous.children.remove(currentNode);
							break;
						}
					}

					currentNode.isWordIndicator = false;
					return true;
				}
			}
			return false;
		}

		/**
		 * This method is overridden to modify the output.
		 *
		 ** @return String Returns concatenated view of the Data structure.
		 */

		@Override
		public String toString() {
			return "DataTree [rootNode=" + rootNode + "]";
		}

		/**
		 * This class Node is used to create the nodes for the Data Tree.
		 * 
		 * @param content
		 *            The value of the character.
		 * @param children
		 *            The child node of the current node.
		 * @param isWordIndicator
		 *            Indicates whether the letter is the end of a valid word.
		 * @param occurence
		 *            The number of times the letter and the word occurs.
		 * 
		 */

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

			/**
			 * This method is used to override the toString() method and modify
			 * the output
			 */

			@Override
			public String toString() {
				return "Node [content=" + content + ", isWordIndicator=" + isWordIndicator + ", occurence=" + occurence
						+ ", children=" + children + "]";
			}

		}

	}
}
