package string;

import java.util.StringTokenizer;

public class Capitalize {

	public static void capfirst(String str) {
		StringTokenizer tok = new StringTokenizer(str, " ");
		StringBuilder sb = new StringBuilder();
		String current = null;
		while (tok.hasMoreTokens()) {
			current = tok.nextToken();
			char[] carr = current.toCharArray();
			System.out.println(carr);
			char c = Character.toUpperCase(carr[0]);
			System.out.println(c);
			carr[0] = c;
			System.out.println(carr);
			current = String.valueOf(carr);
			System.out.println(current);
			sb.append(current);
			sb.append(" ");
		}

		str = sb.toString();
		System.out.println(str);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "hello world";
		capfirst(str);

	}

}

/*  String LetterCapitalize(String input) { 
  
    String output = "";
	boolean capitalize = true;
		
	for( int i = 0; i < input.length(); i++ ){
		if( capitalize ){
			output = output + Character.toUpperCase(input.charAt(i));
			capitalize = false;
		} else{
			output = output + input.charAt(i);
			if( input.charAt(i) == ' ' ){
				capitalize = true;
			}
		}
	}
		
	return output;*/
