package recursion;

public class Reversestring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str ="Hello World";
		String rev = reverserecur(str);
		System.out.println("original: "+str);
		System.out.println("reverse: "+rev);
	}
		
		public static String reverserecur(String str){
			if(str.length()<=1){
				return str;
			}
			return reverserecur(str.substring(str.length()/2, str.length())) +reverserecur(str.substring(0,str.length()/2));
		}

	}


