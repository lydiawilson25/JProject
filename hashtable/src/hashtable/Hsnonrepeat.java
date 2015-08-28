package hashtable;

import java.util.ArrayList;
import java.util.HashSet;

public class Hsnonrepeat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "hello";
		System.out.println(firstnonrepeat(str));

	}

	public static char firstnonrepeat(String str) {
		HashSet<Character> hsetrepeat = new HashSet<Character>();
		ArrayList<Character> hlistnonrepeat = new ArrayList<Character>();

		for (char c : str.toCharArray()) {
			if (hsetrepeat.contains(c))
				continue;
			else if (hlistnonrepeat.contains(c)) {
				hlistnonrepeat.remove((Character) c);
				hsetrepeat.add(c);
			} else {
				hlistnonrepeat.add(c);
			}
		}

		return hlistnonrepeat.get(0);

	}

}
/*
 * Finds first non repeated character in a String in just one pass.
 * It uses two storage to cut down one iteration, standard space vs time
 * trade-off.Since we store repeated and non-repeated character separately,
 * at the end of iteration, first element from List is our first non
 * repeated character from String.
 */


//Read more: http://javarevisited.blogspot.com/2014/03/3-ways-to-find-first-non-repeated-character-String-programming-problem.html#ixzz3hcaRDV2p
