package linkedlistex;

class ListNode {
	int data;
	ListNode next;

	public ListNode(int data) {
		this.data = data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getData() {
		return data;
	}



	public void getLength() {
		int length = 0;
		ListNode currentNode = this;
		while (currentNode != null) {
			length++;
			currentNode=currentNode.next;
		}
		System.out.println("The length is: " + length);
	}
	public void printList(){
		ListNode currentNode = this;
		while(currentNode!=null){
			System.out.print(currentNode.getData()+" ");
			currentNode=currentNode.next;
			
		}
	}

	public ListNode getNext() {
		// TODO Auto-generated method stub
		return this;
	}

	public void setNext(ListNode head) {
		// TODO Auto-generated method stub
		this.next=head;
		
	}
}

public class Singlylinkedlist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	int x = 3;
		ListNode n1 = new ListNode(x);
		x = 4;
		ListNode n2 = new ListNode(x);
		/*n1.setNode(n2);
		x = 9;
		ListNode n3 = new ListNode(x);
		n2.setNode(n3);
		n1.getLength();
		System.out.println("The elemnts in the array are: ");
		n1.printList();
*/
	}

}
