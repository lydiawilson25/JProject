package aspera;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Longestword2 {
static int COUNT=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String filename = "C:/Users/Wilson/workspace/aspera/src/aspera/test.txt";
		String filename = "C:/Users/Wilson/workspace/aspera/src/aspera/words20.txt";
		//readfile(filename);
		String output = readEntireFile(filename);
		Set<String> sarr = new HashSet<String>();
		//readEntireFile(filename);
		String[] splitted = output.split("\n");
		for(String s: splitted)
			sarr.add(s);
		System.out.println("sarr size: "+sarr.size());
		callwordbreak(sarr);
	}
	
	   private static String readEntireFile(String filename) throws IOException {
	        FileReader in = new FileReader(filename);
	        StringBuilder contents = new StringBuilder();
	        char[] buffer = new char[4096];
	        int read = 0;
	        do {
	            contents.append(buffer, 0, read);
	            read = in.read(buffer);
	        } while (read >= 0);
	        return contents.toString();
	    }

	public static void callwordbreak(Set<String> sarr) {
		int len = 0, max = 0;
		String maxword = null;
		for (String s : sarr) {
			if (wordbreak(s, sarr) == true) {
				len = s.length();
				System.out.println(len);
				if (len > max) {
					max = len;
					maxword = s;
					System.out.println("len: "+len+" s: "+s);
				}

			}
		}
		System.out.println("\nthe word is: " + maxword);
	}

	public static boolean wordbreak(String input, Set<String> dict) {
		System.out.println("count:"+COUNT++ +"  Analyzing:"+input);
		boolean[] t = new boolean[input.length() + 1];
		t[0] = true;
		String orig = input;
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
				if (!input.substring(i, end).equals(orig) && input.substring(i, end).equals(a)) {
					t[end] = true;
				}
			}
		}
		return t[input.length()];
	}

}
