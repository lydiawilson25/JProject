package linkedlistex;

import java.util.Scanner;

class Node4 {
	int data;
	Node4 next=null;
	public Node4(){
		data=0;
	}
	public Node4(int num){
		data=num;
	}
	
	void addnode(int num){
		Node4 end = new Node4(num);
		Node4 current = this;
		while(current.next!=null){
			current=current.next;
		}
		current.next=end;
		current.next.next=null;
	}
	
	void printlist(){
		Node4 current =this;
		while(current!=null){
		System.out.print("  "+current.data);
		current=current.next;
		}
		//System.out.println("exit");
	}
	
	void findlength(){
		Node4 current;
		int count=0;
		current = this;
		while(current.next!=null){
			count++;
			current=current.next;
		}
		System.out.println("\nThe length is: "+count);
	}
	
	void insertnode(int pos, int num){
		int count=0;
		Node4 current;
		Node4 temp =null;
		current=this;
		while(current.next!=null) {
			count++;
			
			if (count==pos){
System.out.println("The position is: "+pos);
			}
		}
		System.out.println("The length is: "+count);
	}

}

public class Listlength {

	public static void main(String[] args) {
	Node4 newnode = new Node4(4);	
	newnode.addnode(5);	
	newnode.addnode(6);	
	newnode.addnode(7);
	System.out.println("The elements in array is: ");
	newnode.printlist();
	newnode.findlength();
	newnode.insertnode(2, 44);
	System.out.println("Now The elements in array is: ");
	newnode.printlist();

}
}
