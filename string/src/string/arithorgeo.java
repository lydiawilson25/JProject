package string;

import java.util.*;
import java.io.*;

public class arithorgeo {

	String ArithGeo(int[] arr) {
		int diff1 = -1, ratio1 = 0;
		Boolean arith = false, geo = false;
		for (int i = 0; i < arr.length; i++) {
			int diff = Math.abs(arr[i + 1] - arr[i]);
			int ratio = arr[i + 1] / arr[i];
			if (diff1 == -1) {
				diff1 = diff;
			} else {
				if (diff1 == diff) {
					arith = true;
				}
			}
			if (ratio1 == 0) {
				ratio1 = ratio;
			} else {
				if (ratio1 == ratio)
					geo = true;
			}
		}
		if (arith == true)
			return "arith";
		else if (geo == true)
			return "geo";
		else
			return "-1";

	}

	public static void main(String[] args) {
		// keep this function call here
		Scanner s = new Scanner(System.in);
		arithorgeo c = new arithorgeo();
		String str = s.nextLine();
		String[] items = str.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		int[] arr = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			arr[i] = Integer.parseInt(items[i]);
		}
		
		//System.out.print(c.ArithGeo(s.nextLine()));
	}

}
