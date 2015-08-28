package queue;

import java.util.LinkedList;
import java.util.Queue;

public class actualqueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue q1 = new LinkedList();
		q1.add(2);
		q1.add(9);
		q1.add(3);
		q1.add(7);
		System.out.println(q1.element());
		System.out.println(q1.remove());
		System.out.println(q1.element());
	}

}
