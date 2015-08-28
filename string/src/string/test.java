package string;

import java.util.Arrays;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "[1,2]";
		exoh(str);

	}

	public static void exoh(String str) {
		String[] items = str.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		int[] arr = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			arr[i] = Integer.parseInt(items[i]);
		}
		for (int i : arr) {
			System.out.println(i);
		}
	}
}