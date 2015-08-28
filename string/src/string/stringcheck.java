package string;

import java.util.StringTokenizer;

public class stringcheck {

	public static Boolean checkstring(String str) {
		int i,len=str.length();
	    if(str==null) return false;
	    for(i=0;i<len;i++){
	    	if(Character.isLetter(str.charAt(0)))
	    		return false;
	    	else if(str.charAt(i)=='+' || str.charAt(i)=='='){
	        continue;
	    }
	      else   	  
	    	  if(Character.isLetter(str.charAt(i))){
	       if(str.charAt(i-1)=='+' && str.charAt(i+1)=='+'){
	         return true;
	       }
	        else 
	        	return false;
	      }
	   

	}
	    return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "f++d+";
		System.out.println(checkstring(str));

	}

}