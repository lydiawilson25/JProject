package altair;

import java.util.Arrays;

public class Index2 {
	public static void main(String args[]) {
		int arr[] = { -3, 1, 5, 3, -1 };
		System.out.println("our aim is to find the number which is equal to its index");
		System.out.println("the array is:");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println("\nfirst sort the array");
		Arrays.sort(arr);
		System.out.println("now the array is:");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		int number = findtheindex(arr);
		System.out.print("the number we want is: "+number);
	}

	public static int findtheindex(int arr[]) {
		int leftindex = 0;
		int rightindex = arr.length - 1;
		
		while (rightindex - leftindex > 0) {
			int middleindex = rightindex - leftindex / 2;
			if (arr[middleindex] == middleindex) {
				return middleindex;
			}
			if (arr[middleindex] > middleindex) {
				rightindex = middleindex - 1;
			} else {
				leftindex = middleindex + 1;

		}
	}
		return -1;
}}
