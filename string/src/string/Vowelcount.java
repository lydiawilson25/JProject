package string;

import java.util.*;
import java.io.*;

public class Vowelcount {

	static int vowelcount(String str) {

		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o'
					|| str.charAt(i) == 'u')
				count++;
		}

		return count;

	}

	public static void main(String[] args) {
		// keep this function call here
		String str = "hello";
		System.out.println(vowelcount(str));
	}

}