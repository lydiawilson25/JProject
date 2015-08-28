package string;

public class Stringbuilderex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder build1 = new StringBuilder("Java");
		System.out.println(build1);
		System.out.println("insert: ");
		build1.insert(0, "Hello ");
		System.out.println(build1);
		System.out.println("append");
		build1.append("!");
		System.out.println(build1);
		System.out.println("replace");
		build1.replace(0, 2, "newly");
		System.out.println(build1);
		System.out.println("delete");
		build1.delete(3, 4);
		System.out.println(build1);
	}
}
