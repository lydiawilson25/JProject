import java.util.ArrayList;

public class Simplearraylist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> elements = new ArrayList<Integer>();
		elements.add(10);
		elements.add(15);
		elements.add(9);

		System.out.println("the size is: " + elements.size());
		System.out.println("the elements are: ");
		for (int i = 0; i < elements.size(); i++) {
			System.out.println(elements.get(i));
		}
	}

}
