package searching;

import java.util.Arrays;

public class Binarysearchstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strarray = { "apple", "banana", "orange", "grapes" };
		String value = "grapes";
		System.out.println("The array is: ");
		for (String s : strarray) {
			System.out.println(s);
		}
		Arrays.sort(strarray);
		System.out.println("The array is: ");
		for (String s : strarray) {
			System.out.println(s);
		}
		int pos = binarysearch(strarray, value, 0, strarray.length);
		System.out.println("the position of value: " + value + " is " + (pos+1));
	}

	public static int binarysearch(String[] strarray, String value, int min, int max) {
		if(min>max){
			return -1;
		}
		int mid = (min + max) / 2;
		if (strarray[mid].equals(value))
			return mid;
		else if (strarray[mid].compareTo(value) > 1) {
			binarysearch(strarray, value, min, mid - 1);
		} else {
			binarysearch(strarray, value, mid + 1, max);
		}
		return -1;
	}

}
