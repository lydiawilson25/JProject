package linkedlistex;

import java.util.Scanner;

public class Addnodeback {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num=3;
		//System.out.println("Enter numbers one by one and then press 0");
		//Scanner in = new Scanner(System.in);
		//num=in.nextInt();
		//ListNode3 node;
		//while(num!=0){
		//	node = new ListNode3(num);
		//	node.addNodeEnd(node);			
		//}
		ListNode3 node = new ListNode3();
		node.setData(num);
		node.addNodeEnd();
		node.printlist();

	}

}

class ListNode3 {
	int data;
	ListNode3 next;
	ListNode3 head;
	int length=0;
	
	public ListNode3(){
		this.data=0;
	}

	public ListNode3(int data) {
		this.data = data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setNext(ListNode3 newnode) {
		this.next = newnode;
	}

	public int getData() {
		return this.data;
	}

	public ListNode3 getListNode() {
		return this;
	}
	
	public void addNodeEnd(){
		ListNode3 currentNode=this;
		currentNode.setNext(head);
		currentNode=head;
		length++;
	}
	public void printlist(){
		ListNode3 currentNode = this;
		System.out.println("The array is: ");
		while(currentNode!=null){
			System.out.println(currentNode.getData());
		}
	}

}
