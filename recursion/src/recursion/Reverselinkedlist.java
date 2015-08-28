package recursion;

import java.util.Scanner;

public class Reverselinkedlist {
	private Node head;

	private static class Node {
		private int value;
		private Node next;

		public Node(int value) {
			this.value = value;
		}
	}

	public void addNode(Node newnode) {
		
		Node current = head;
		if (head == null)
			head = newnode;
		else {
			while (current.next != null) {
				current = current.next;
			}
			current.next = newnode;
			newnode.next=null;
		}
	}
	
	public static void printlist(Node head){
		Node current = head;
		System.out.println("\nThe list is: ");
		while(current!=null){
			System.out.print(current.value+" ");
			current = current.next;
		}
		
	}
	
	public static Node reverselist(Node currentnode){
		Node prevnode=null,nextnode;
		while(currentnode!=null){
			nextnode = currentnode.next;
			currentnode.next=prevnode;
			prevnode=currentnode;
			currentnode=nextnode;
		}
		return prevnode;
		
	}
	
	public static void main(String args[]){
		Reverselinkedlist list = new Reverselinkedlist();
		Node head = new Node(5);
		list.addNode(head);
		list.addNode(new Node(4));
		list.addNode(new Node(1));
		list.addNode(new Node(2));
		printlist(head);
		Node reversehead = reverselist(head);
		printlist(reversehead);
	}

}
