import java.util.LinkedList;

public class linkedlisteg {
	@SuppressWarnings("rawtypes")
	public static void main(String args[]) {
		LinkedList newlist = new LinkedList();
		newlist.add(1);
		newlist.add(2);
		newlist.add(3);
		newlist.add(4);
		newlist.add(5);
		System.out.println("The original linked list is:  " +newlist);
		System.out.println("-------------------------------------------------------");
		System.out.println("Add nodes 0 and 6");		
		newlist.addFirst(0);
		newlist.addLast(6);
		System.out.println("The list now is: " +newlist);
		System.out.println("-------------------------------------------------------");
		System.out.println("Remove nodes 0 and 6");
		newlist.removeFirst();
		newlist.removeLast();
		System.out.println("The list is: " +newlist);
		System.out.println("--------------------------------------------------------");
		System.out.println("set and get");
		Object element1 = newlist.get(0);
		System.out.println("The first element is: " +element1);
		Object element2 = 100;
		newlist.set(0, element2);
		System.out.println("The list is: " +newlist);
		System.out.println("--------------------------------------------------------");
		System.out.println("remove first and last");
		newlist.removeFirst();
		newlist.removeLast();
		System.out.println("the list is: " +newlist);
		System.out.println("--------------------------------------------------------");
		System.out.println("add new element in the 3rd postiton");
		newlist.add(2,500);
		System.out.println("the list now is: " +newlist);

		
		
		
		
	}
}