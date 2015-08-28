package hashtable;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;

public class Hashsetex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<Integer> hset = new HashSet<Integer>();
		hset.add(6);
		hset.add(1);
		hset.add(4);
		
		System.out.println("using iterator: ");
		Iterator i = hset.iterator();
		while(i.hasNext()){
			System.out.print(i.next()+" ");
		}
		System.out.println("\nusing enumeration: ");
		Enumeration<Integer> e = Collections.enumeration(hset);
		int count=0;
		while(e.hasMoreElements()){
			System.out.print(e.nextElement());

		}

	}

}
