package recursion;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter number to find factorial");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int n = factorial(num);
		System.out.println("The factorial is: "+n);
		

	}
	
	public static int factorial(int num){
		int fact = 1;
		while(num!=0){
			fact = fact * num;
			num=num-1;
		}
		return fact;
	}

}
