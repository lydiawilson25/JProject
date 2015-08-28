package hacker;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Simple3 {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner in = new Scanner(System.in);
		System.out.println("Enter testcase");
		int testcase = in.nextInt();
		 long[] arr = new long [testcase];
		int i = 0;
		System.out.println("Enter number");
		for (i = 0; i < testcase; i++) {
			arr[i] = in.nextInt();

		}
		long sum = 0;
		for (i = 0; i < arr.length; i++) {
			System.out.println(sum+"+"+arr[i]);
			sum = sum + arr[i];
			
		}
		System.out.println(sum);
	}
}