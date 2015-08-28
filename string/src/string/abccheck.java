package string;

import java.util.*;
import java.io.*;

public class abccheck {
	static Boolean ABCheck(String str) {

		// code goes here
		/*
		 * Note: In Java the return type of a function and the parameter types
		 * being passed are defined, so this return call must match the return
		 * type of the function. You are free to modify the return type.
		 */
		char[] carr = str.toCharArray();
		int len = carr.length;
		for (int i = 0; i < len - 4; i++) {
			if (carr[i] == 'a' && carr[i + 4] == 'b')
				return true;
			else if (carr[i] == 'b' && carr[i + 4] == 'a')
				return true;
			/*else if (carr[len-1] == 'a' && carr[len - 5] == 'b')
				return true;
			else if (carr[len-1] == 'b' && carr[len - 5] == 'a')
				return true;*/
		}
		return false;
	}

	public static void main(String[] args) {
		// keep this function call here
		String str = "after badla";
		System.out.println(ABCheck(str));
	}

}