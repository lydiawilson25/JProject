package altair;

import java.util.ArrayList;

public class TodoArraylist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList todolist = new ArrayList();
		todolist.add("buy cat food");
		todolist.add("watch cat pictures");
		todolist.add("walk the cat");
		String s = (String) todolist.get(1);
		System.out.println(s);

	}

}
