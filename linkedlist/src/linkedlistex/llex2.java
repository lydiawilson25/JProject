package linkedlistex;

import java.util.Scanner;

public class llex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a value for node");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Node2 middle = new Node2(n);
		System.out.println("the value of node is: " +middle);
	}
	
	

}

class Node2{
	int num;
	Node2 next;	
	public Node2(int n) {
		num=n;
		next=null;
	}
}
