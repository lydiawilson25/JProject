package linkedlistex;

class Node3 {
	int data;
	Node3 next = null;

	public Node3() {
		data = 0;

	}

	public Node3(int num) {
		data = num;
	}

	public void addnode(int num) {
		Node3 end = new Node3(num);
		Node3 current = this;
		while (current.next != null) {
			current = current.next;
			System.out.println("indside while loop");
		}
		current.next = end;
		System.out.println("The node is added:  " + current.next.data);
	}

	void printnodelist() {
		Node3 n;
		n = this;
		while (n != null) {
			System.out.println("The data is:" + n.data);
			n = n.next;
		}

	}
}

public class lltutorial {
	public static void main(String args[]) {

		Node3 node1 = new Node3();
		node1.printnodelist();
		Node3 node2 = new Node3(4);
		node2.printnodelist();
		node1.addnode(5);
		node1.printnodelist();

	}

}
