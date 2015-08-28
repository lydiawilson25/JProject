package string;

public class Anagramcheck2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word1 = "Mary";
		String word2 = "May1";
		boolean ans = anagramcheck(word1, word2);
		System.out.println("are the 2 word anagram: " + ans);
	}

	public static boolean anagramcheck(String word1, String word2) {
		if(word1.length()!=word2.length())
			return false;
		char[] word1arr = word1.toLowerCase().toCharArray();
		StringBuilder build2 = new StringBuilder(word2.toLowerCase());
		int pos = -1;
		for (char c : word1arr) {
			pos = build2.indexOf(String.valueOf(c));
			if (pos == -1) {
				return false;
			} else {
				build2.delete(pos,pos+1);
			}
		}
		return true;
	}

}
