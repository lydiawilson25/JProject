package string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Selectlongword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strarray = { "cat", "dog", "dogs",  "catdogs", "dogst","catdogst", "catdogxxxy" };
		int len = strarray.length;
		String curword = null, maxword = null, preword = null;
		for (int i = len - 1; i > 0; i--) {
			curword = strarray[i];

			// System.out.println("current word: "+curword);
			for (int j = i - 1; j >= 0; j--) {
				preword = strarray[j];
				// System.out.println("pre: "+preword);
				if (curword.contains(preword)) {
					curword = curword.replace(preword, "");
					System.out.println("curword: " + curword);
					if (curword.length() == 0) {
						maxword = strarray[i];
						System.out.println("max: " + maxword);
					}
				}

			}

		}
		
		Set<String> dict = new HashSet<String>(Arrays.asList(strarray));
		System.out.println(wordbreak("catdogst",dict));

	}

	public static boolean wordbreak(String input, Set<String> dict) {
		boolean[] t = new boolean[input.length() + 1];
		t[0] = true;
		for (int i = 0; i < input.length(); i++) {
			if (!t[i])
				continue;
			for (String a : dict) {
				int len = a.length();
				int end = i + len;
				if (end > input.length())
					continue;
				if (t[end])
					continue;
				if (input.substring(i, end).equals(a)) {
					t[end] = true;
				}
			}
		}
		return t[input.length()];
	}

}
