import java.util.HashSet;
import java.util.Set;

public class Leetcodedynamic2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> dict= new HashSet<String>();
		dict.add("cats");
		dict.add("catxdogcatsrat");
		dict.add("rat");
		dict.add("hippopotamuses");
		dict.add("cat");
		dict.add("ratcatdogcat");
		dict.add("catsdogcats");
		dict.add("dog");
		dict.add("dogcatsdog");
		System.out.println(wordBreak("catxdogcatsrat",dict));
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
