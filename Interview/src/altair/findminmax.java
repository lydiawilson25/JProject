package altair;

public class findminmax {
	public static void main(String args[]) {
		int[] arr = {4, 3, 5, 1, 2, 6, 9, 2, 10, 11};
		int max = arr[0];
		int min = arr[0];
		int len = arr.length/2;
		for (int i=0;i<len;i++) {
			int num1 = arr[i*2];
			int num2 = arr[i*2+1];
			if (num1>num2) {
				if (num1>max) max=num1;
				if (num2<min) min=num2;
			}
			else {
				if (num2>max) max = num2;
				if(num1<min) min=num2;
			}
		}
		System.out.println("min and max is " +min+" and "+max);
		
	}
}