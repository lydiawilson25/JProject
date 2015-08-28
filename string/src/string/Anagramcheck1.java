package string;

import java.util.Arrays;

public class Anagramcheck1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word1 = "Mary";
		String word2 = "Army";
		String word3 = word1.toLowerCase();
		String word4 = word2.toLowerCase();
		boolean ans = checkanagram(word3, word4);
		System.out.println("The two words are anagram: " + ans);
	}

	public static boolean checkanagram(String word3, String word4) {
		if (word3.length() != word4.length()) {
			return false;
		}
		int i, len = word3.length();
		char[] word3arr = word3.toCharArray();
		char[] word4arr = word4.toCharArray();
		Arrays.sort(word3arr);
		Arrays.sort(word4arr);
		for (char c : word3arr)
			for (i = 0; i < len; i++) {
				if (Character.toLowerCase(word3arr[i]) == Character.toLowerCase(word4arr[i])) {
					continue;
				} else
					return false;
			}
		return true;
		}
}
