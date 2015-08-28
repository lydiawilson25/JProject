package string;

import java.util.ArrayList;
import java.util.Arrays;

public class Checksub {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strarray = { "cat", "dog", "dogs", "dogst" };
		ArrayList<String> strlist = new ArrayList<String>(Arrays.asList(strarray));
		String str = "abc";//"catdogst";
		int count = 0;
		int len = str.length();
		int i = 0;
		for (int j = str.length(); j > 1; j--) {
			while (str != null) {
				String cursub = str.substring(i, j);
				// System.out.println("i: "+i+" j: "+j);
				// System.out.println(cursub);
				if (strlist.contains(cursub)) {
					// System.out.println("current substring: " + str);
					str = str.replace(cursub, "");
					count++;
					// System.out.println("current substring after replace: " +
					// str);
					j = str.length();
					if (j == 0)
						break;
				} else
					break;
			}
		}
		System.out.println("no of words: " + count);

	}

}
