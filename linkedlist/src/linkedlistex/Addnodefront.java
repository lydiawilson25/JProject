package linkedlistex;

import java.util.Scanner;

public class Addnodefront {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode2 n1 = new ListNode2(3);
		n1.printList();
		System.out.println("\nEnter a number to add in front ");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		n1.addNode(num);
		

	}

}

class ListNode2 {
	int data;
	ListNode2 next;

	public ListNode2(int data) {
		this.data = data;
	}

	public void addNode(int data) {
		ListNode2 headNode = this;
		ListNode2 newNode = new ListNode2(data);
		newNode.next = headNode;
		newNode.printList();
	}

	public void printList() {
		ListNode2 currentNode = this;
		System.out.println("The list is: ");
		while (currentNode != null) {
			System.out.print(currentNode.data + " ");
			currentNode = currentNode.next;
		}
	}
}
