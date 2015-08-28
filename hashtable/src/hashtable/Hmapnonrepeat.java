package hashtable;

import java.util.HashMap;

public class Hmapnonrepeat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str ="hello";
		System.out.println(firstnonrepeat(str));
		

	}
	
	public static char firstnonrepeat(String str){
		HashMap<Character,Integer> hmap = new HashMap<Character, Integer>();
		int len=str.length();
		for(int i=0;i<len;i++){
			char c = str.charAt(i);
			if(hmap.containsKey(c)){
				hmap.put(c, hmap.get(c)+1);
			}
			else {
				hmap.put(c,1);
			}
		}
		
		for(int i=0;i<len;i++){
			char c = str.charAt(i);
			if(hmap.get(c)==1){
				return c;
			}
		}
		return 'N';
		
	}

}
