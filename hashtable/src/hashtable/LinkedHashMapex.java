package hashtable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class LinkedHashMapex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedHashMap lm = new LinkedHashMap();
		lm.put("apple", "red");
		lm.put("banana", "yellow");
		lm.put("orange", "orange");
		Set lmset = lm.entrySet();
		Iterator i = lmset.iterator();
		System.out.println("Linkedhashmap entries: ");
		while(i.hasNext()){
			System.out.println(i.next());
		}

	}

}
