package string;

public class Substrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "fun";
		String sub = null;
		int length = str.length();
		  for(int c = 0 ; c < length ; c++ )
	      {
	         for(int i = 1 ; i <= length - c ; i++ )
	         {
	            sub = str.substring(c, c+i);
	            System.out.println(sub);
	         }
	      }

	}

}
