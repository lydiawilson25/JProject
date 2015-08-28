package trees;

public class Binarysearchtree2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node2 n2 = new Node2(51);
		n2.insert(n2,3);
		n2.insert(n2,10);
		n2.insert(n2,77);
		n2.insert(n2,20);
		n2.insert(n2,40);
		n2.insert(n2,34);
		n2.insert(n2,28);
		n2.insert(n2,61);
		System.out.println(n2.max(n2));

	}

}

class Node2 {
	protected Node2 left;
	protected Node2 right;
	protected int value;

	public Node2(int value) {
		this.value = value;
	}

	public void search(Node2 n, int value) {
		if (n.value == value || n == null) {
			System.out.println("Node found: " + n.value);
		} else if (value < n.value) {
			search(n.left, value);
		} else {
			search(n.right, value);
		}

	}

	public void insert(Node2 n, int value) {
		if (value < n.value) {
			if (n.left != null) {
				insert(n.left, value);
			} else {
				n.left = new Node2(value);
			}
		}

		if (value > n.value) {
			if (n.right != null) {
				insert(n.right, value);
			} else {
				n.right = new Node2(value);
			}
		}
	}

	public int min(Node2 n) {
		while (n != null) {
			n = n.left;
		}
		return n.value;
	}

	public int max(Node2 n) {
		while (n != null) {
			n = n.left;
		}
		return n.value;
	}
}
