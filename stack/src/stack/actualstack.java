package stack;

import java.util.Stack;

public class actualstack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack s1 = new Stack();
		s1.push(3);
		s1.push(7);
		s1.push(9);
		System.out.println(s1.pop());
		System.out.println(s1.peek());
		System.out.println(s1.pop());
		System.out.println(s1.pop());

	}

}
