package codebyter;

import java.util.*;
import java.io.*;

public class Stringify {
	public static String DivisionStringified(int num1, int num2) {
		// divide num1/num2->if there is thousands place then put comma
		// return string.//use string builder//if only three places then convert
		// int to string//else add three digits one comma and rest
		// while ans/1000==0
		int ans = num1 / num2;
		StringBuilder sb = new StringBuilder();

		if (ans / 1000 == 0)
			sb.append(Integer.toString(ans));
		else {
			while (ans != 0) {
				String curnum = Integer.toString(ans % 1000);
				sb.insert(0, curnum);
				ans = ans / 1000;
				if (ans % 1000 != 0)
					sb.insert(0, ",");
			}
		}
		return String.valueOf(sb);
	}

	public static void main(String[] args) {
		// keep this function call here
		// Scanner s = new Scanner(System.in);
		// Stringify c = new Stringify();
		int num1 = 123456789, num2 = 10;
		System.out.print(DivisionStringified( 503394930 , 43 ));
	}

}
