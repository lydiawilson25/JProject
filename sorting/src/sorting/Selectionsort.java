package sorting;

public class Selectionsort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 20, 12, 45, 19, 91, 55 };
		printarr(arr);
		selectionsort(arr);
	}

	public static void selectionsort(int[] arr) {
		int out, in, len = arr.length, min;
		for (out = 0; out < len - 1; out++) {
			min = out;
			for (in = out + 1; in < len; in++) {
				if (arr[min] > arr[in]) {
					min = in;
				}
				swap(out, min, arr);
			}
			printarr(arr);
		}
		printarr(arr);
	}

	public static void printarr(int[] arr) {
		int i, len = arr.length;
		System.out.println("\nThe array is: ");
		for (i = 0; i < len; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void swap(int first, int second, int[] arr) {
		int temp;
		temp = arr[first];
		arr[first] = arr[second];
		arr[second] = temp;
	}

}

	


