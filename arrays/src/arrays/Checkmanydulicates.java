package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Checkmanydulicates {

	public static void main(String[] args) {
		String str = "abcdddefghiiii";
		checkrepeats(str);
	}

	public static void checkrepeats(String str) {
		int i, len = str.length();
		char[] charArray = str.toCharArray();
		HashMap<Character, Integer> hmap = new HashMap<Character, Integer>();
		for (char c : charArray) {
			if (hmap.containsKey(c)) {
				hmap.put(c, hmap.get(c) + 1);
			} else
				hmap.put(c, 1);
		}
		printlist(hmap);
	}

	public static void printlist(HashMap hmap) {
		HashMap<Character, Integer> newmap = hmap;
		for (HashMap.Entry<Character, Integer> hentry : newmap.entrySet()) {
			if (hentry.getValue() > 1) {
				System.out.println("Duplicate: " + hentry.getKey() + " " + hentry.getValue());
			}

		}
	}

}
