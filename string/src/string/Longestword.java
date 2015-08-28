package string;

import java.util.StringTokenizer;

public class Longestword {
	
	public static void findlong(String str){
		int max=0;
		String maxword=null;
		StringTokenizer tok = new StringTokenizer(str," ");
		while(tok.hasMoreTokens()){
			String current = tok.nextToken();
			current=current.replaceAll("\\W", "");
			if(max<current.length()){
				max=current.length();
				maxword=current;
			}
			
			
		}
		System.out.println(maxword);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "This!!!!! is the longest word";
		findlong(str);

	}

}
