package sorting;

public class Bubblesort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 20, 12, 45, 19, 91, 55 };
		bubblesort(arr);
	}

	public static void bubblesort(int[] arr) {
		int in, out, len = arr.length;
		for (out = len - 1; out > 0; out--) {
			for (in = 0; in < out; in++) {
				if (arr[in] > arr[in + 1])
					swap(in, in + 1, arr);
				printarr(arr);
			}
		}
		printarr(arr);
	}

	public static void swap(int first, int sec, int[] arr) {
		int temp;
		temp = arr[first];
		arr[first] = arr[sec];
		arr[sec] = temp;
	}

	public static void printarr(int[] arr) {
		System.out.println("\nThe array elements are: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
