package hashtable;

import java.security.KeyStore.Entry;

import java.util.*;
import java.util.Map.*;

public class Lhmnonrepeat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "hello";

		System.out.println(firstnonrepeat(str1));
	}

	public static char firstnonrepeat(String str) {
		Map<Character, Integer> counts = new LinkedHashMap<Character, Integer>(str.length());

		for (char c : str.toCharArray()) {
			counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);

		}
	

	Set lmset = counts.entrySet();
	Iterator i = lmset.iterator();System.out.println("Linkedhashmap entries: ");while(i.hasNext())

	{
			System.out.println(i.next());
		}

	for(

	Map.Entry<Character, Integer> entry:counts.entrySet())

	{
		System.out.println(entry.getValue());
		if (entry.getValue() == 1)
			return entry.getKey();
	}

	return'N';
}}
/*
 * Using LinkedHashMap to find first non repeated character of String
 * Algorithm :
 *            Step 1: get character array and loop through it to build a 
 *                    hash table with char and their count.
 *            Step 2: loop through LinkedHashMap to find an entry with 
 *                    value 1, that's your first non-repeated character,
 *                    as LinkedHashMap maintains insertion order.
 */


//Read more: http://javarevisited.blogspot.com/2014/03/3-ways-to-find-first-non-repeated-character-String-programming-problem.html#ixzz3hcXdstal
