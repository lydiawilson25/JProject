package sorting;

public class Insertionsortex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = { 4, 10, 9, 2};
		insertionsort(num);

	}

	public static void insertionsort(int[] num) {
		int len = num.length;
		int i, j;
		for (i = 1; i < len; i++) {
			int temp = num[i];
			for (j = i - 1; j >= 0 && temp < num[j]; j--) {
				num[j + 1] = num[j];
				printlist(num);
			}
			num[j + 1] = temp;
			printlist(num);
		}
	}

	public static void printlist(int[] num) {
		System.out.print("The array: ");
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println("\n");
	}

}
