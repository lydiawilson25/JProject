package stack;

import java.util.Scanner;
import java.util.Stack;

public class Stackex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		Stack st = new Stack();
		System.out.println("Enter a number to push inside stack");
		Scanner in =new Scanner(System.in);
		num = in.nextInt();
		st.push(num);
		
		System.out.println("Enter a number to push inside stack");
		in =new Scanner(System.in);
		num = in.nextInt();
		st.push(num);
		System.out.println("Enter a number to push inside stack");
		in =new Scanner(System.in);
		num = in.nextInt();
		st.push(num);
		System.out.println("The stack is: "+st);		
		st.pop();
		System.out.println("After popping from stack: "+st);

	}

}
