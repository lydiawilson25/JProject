import java.util.HashSet;
import java.util.Set;

public class Leetcodedynamic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> dict = new HashSet<String>();
		dict.add("i");
		dict.add("like");
		dict.add("icecream");
		dict.add("samsung");
		dict.add("mobile");
		dict.add("ice");
		dict.add("mango");
		dict.add("man");
		dict.add("go");
		System.out.println(wordBreak("ilikeicecream", dict));

	}

	public static boolean wordBreak(String input, Set<String> dict) {
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
