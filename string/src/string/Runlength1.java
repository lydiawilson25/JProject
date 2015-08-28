package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Runlength1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str =   "wwwbbbw";
		System.out.println(runlength(str));

	}
	
	public static String runlength(String str){
		StringBuilder sb = new StringBuilder();
		int n;
		HashMap<Character,Integer> hmap = new HashMap<Character,Integer>();
		char[] carr = str.toCharArray();
		for(char c: carr){
			if(hmap.containsKey(c))				
				hmap.put(c, hmap.get(c)+1);
			else
				hmap.put(c,1);
		}
		for(Map.Entry et:hmap.entrySet()){
			sb.append(et.getValue());			
			sb.append(et.getKey());
			//System.out.println(sb);
		}
		str = sb.toString();
		return str;
	}
	

}
