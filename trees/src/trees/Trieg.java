package trees;

import java.util.LinkedList;
import java.util.Scanner;

public class Trieg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Trie t = new Trie();
		System.out.println("Trie Test\n");
		char ch;
		do {
			System.out.println("\nTrie Operations\n");
			System.out.println("1. insert ");
			System.out.println("2. delete");
			System.out.println("3. search");
			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter string element to insert");
				t.insert(scan.next());
				break;
			case 2:
				System.out.println("Enter string element to delete");
				try {
					t.remove(scan.next());
				} catch (Exception e) {
					System.out.println(e.getMessage() + " not found ");
				}
				break;
			case 3:
				System.out.println("Enter string element to search");
				System.out.println("Search result : " + t.search(scan.next()));
				break;
			default:
				System.out.println("Wrong Entry \n ");
				break;
			}
			System.out.println("\nDo you want to continue (Type y or n) \n");
			ch = scan.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
	}
}

class TrieNode {
	char content;
	boolean isend;
	int count;
	LinkedList<TrieNode> childlist;

	public TrieNode(char c) {
		childlist = new LinkedList<TrieNode>();
		isend = false;
		content = c;
		count = 0;
	}

	public TrieNode subNode(char c) {
		if (childlist != null) {
			for (TrieNode eachchild : childlist) {
				if (eachchild.content == c)
					return eachchild;
			}

		}
		return null;
	}
}

class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode(' ');
	}

	public void insert(String word) {
		if (search(word) == true)
			return;
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.subNode(ch);
			if (child != null)
				current = child;
			else {
				current.childlist.add(new TrieNode(ch));
				current = current.subNode(ch);
			}
			current.count++;
		}
		current.isend = true;
	}

	public boolean search(String word) {
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			if (current.subNode(ch) == null)
				return false;
			else
				current = current.subNode(ch);
		}
		if (current.isend == true)
			return true;
		return false;
	}

	public void remove(String word) {
		if (search(word) == false) {
			System.out.println(word + " does not exist in trie\n");
			return;
		}
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.subNode(ch);
			if (child.count == 1) {
				current.childlist.remove(child);
				return;
			} else {
				child.count--;
				current = child;
			}
		}
		current.isend = false;
	}
}
