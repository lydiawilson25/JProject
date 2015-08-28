package altair;

import java.util.Arrays;

public class mindiff {
	public static void main(String args[]) {
		int arr[] = {64,57,2,78,43,73,53,86};
		System.out.println("The array is: ");
		for (int i=0;i<arr.length;i++) {
			System.out.print("  " +arr[i]);
		}
		Arrays.sort(arr);
		System.out.println("\nSorted array is: ");
		for (int i=0;i<arr.length;i++) {
			System.out.print("  " +arr[i]);
		}
		int mindiff = Integer.MAX_VALUE;
		for(int i=0;i<arr.length-1;i++) {
			int num1 = arr[i];
			int num2 = arr[i+1];
			int diff = num2-num1;
			if (diff<mindiff) mindiff=diff;
		}
		System.out.println("\nthe min diff is: "+mindiff);
		
	}

}
