package altair;

import java.util.Scanner;

public class Linkedlistnodereverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter some integers ending with 0 \n");
		Scanner input = new Scanner(System.in);
		Node top, np, last = null;
		top = null;
		int n = input.nextInt();
		while (n != 0) {
			np = new Node(n);
			np.next = top;
			top = np;
			n = input.nextInt();

		}
		System.out.println("The items in the list are: \n");
		printList(top);
	}

	public static void printList(Node top) {
		while (top != null) {
			System.out.printf("%d", top.num);
			top = top.next;
		}
		System.out.printf("\n");
	}

}
 class Node {
	 int num;
	 Node next;
	 public Node(int n){
		 num=n;
		 next=null;
	 }
 }
