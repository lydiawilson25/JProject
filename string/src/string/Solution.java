package string;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		String str = "welcometojava";
		int limit = 3;
		findword(str, limit);
		System.out.println("The string are: ");
	}

	public static void findword(String str, int limit) {
		
		int len = str.length();
		char min = 'z', max = 'a';
		for (int i = 0; i < len - limit+1; i++) {
			if (str.charAt(i) < min)
				min = str.charAt(i);
			if (str.charAt(i) > max) {
				max = str.charAt(i);
			}
		}
		
		System.out.println(str.indexOf(min));
		String small = str.substring(str.indexOf(min), str.indexOf(min) + limit );
		String big = str.substring(str.indexOf(max), str.indexOf(max) + limit );
		System.out.println("The string are:"+small + " " + big);
	}
}