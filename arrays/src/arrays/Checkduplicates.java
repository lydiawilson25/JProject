package arrays;

import java.awt.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Checkduplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 1, 2, 3, 4, 4, 3, 6 };
		checkduplicates(arr);
	}

	public static void checkduplicates(int[] arr) {
		int[] arr1 = arr;
		int i, len = arr.length;
		Set hset = new HashSet();
		for (i = 0; i < len; i++) {
			if (hset.add(arr1[i]) == false) {
				System.out.println("duplicate found: " + arr1[i]);
			}
		}
	}
}
