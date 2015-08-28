package string;

public class Palindrom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "racecar";
		String s2 = "maam";
		String s3 = "string";
		checkpalindrome(s1);
		checkpalindrome(s2);
		checkpalindrome(s3);
		
		
	}

	private static void checkpalindrome(String s3) {		
		String rev = reverse(s3);
		if(s3.equalsIgnoreCase(rev)) System.out.println("The string is palindrom : "+s3);
		else System.out.println("The string is not palindrome: "+s3);
	}

	private static String reverse(String s3) {
		// TODO Auto-generated method stub
		int len=s3.length();
		String rev="";
		for(int i=0,j=len-1;i<len;i++,j--){
			rev=rev+s3.charAt(j);			
		}
		return rev;
	}

}
