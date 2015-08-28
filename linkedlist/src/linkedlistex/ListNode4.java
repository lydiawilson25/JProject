package linkedlistex;

public class ListNode4 {
	int data;
	static ListNode4 head;
	ListNode4 next = null;
	static int length = 0;

	public ListNode4(int data) {
		this.data = data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public static void setHead(ListNode4 newhead) {
		head = newhead;
	}

	public void setNext(ListNode4 newnext) {
		this.next = newnext;

	}

	public int getData() {
		return this.data;
	}

	public ListNode4 getHead() {
		return this.head;
	}

	public ListNode4 getNext() {
		return this.next;
	}

	public static void addnode(int data) {
		ListNode4 temp = head;
		if (head == null) {
			ListNode4 newnode = new ListNode4(data);
			setHead(newnode);
			newnode.setNext(null);
			System.out.println(newnode.getHead().getData());
			length++;
		} else {

			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			ListNode4 newnode = new ListNode4(data);
			temp.setNext(newnode);
			newnode.setHead(head);
			newnode.setNext(null);
			length++;

		}
	}

	public static void insertnode(int data, int pos) {
		ListNode4 temp = head;
		if(pos>length) System.out.println("Enter correct position");
		for (int i = 2; i < pos; i++) {
			temp = temp.getNext();
		}
		ListNode4 newnode = new ListNode4(data);
		newnode.setNext(temp.next);
		temp.setNext(newnode);
		length++;
	}

	public static void printlist() {
		ListNode4 current = head;
		System.out.println("the length is: " + length + "\nThe list is: ");
		while (current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
	}

	public static void main(String args[]) {
		addnode(1);
		addnode(2);
		// printlist();
		insertnode(3, 3);
		insertnode(4, 4);
		insertnode(5, 3);
		printlist();
	}

}
