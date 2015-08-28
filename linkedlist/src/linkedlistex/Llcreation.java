package linkedlistex;

import java.util.Scanner;

//import org.w3c.dom.Node;

public class Llcreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter some integers and end with 0 \n");
		Scanner in = new Scanner(System.in);
		Node1 top,np,last=null;
		top=null;
		int n=in.nextInt();
		while(n!=0) {
			np = new Node1(n);
			if (top==null){
				top=np;
			}
			else last.next=np;
			last=np;
			n=in.nextInt();			
		}
		System.out.println("The items in the list are: ");
		printList(top);

	}
	public static void printList(Node1 top){
		while(top!=null){
			System.out.print("  " +top.num);
			top=top.next;			
		}
		System.out.println("\n");
		
	}

}

 class Node1 {
	int num;
	Node1 next;
	public Node1(int n){
		num=n;
		next=null;
	}
}
