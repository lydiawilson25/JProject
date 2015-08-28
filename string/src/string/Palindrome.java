package string;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str =  "never odd or even";
		System.out.println(checkpalin(str));

	}
	
	public static boolean checkpalin(String str){
		String temp = str;
		StringBuilder sb = new StringBuilder();
		temp=temp.replaceAll("\\W", "");
		for(int j=temp.length()-1;j>=0;j--){
			sb.append(temp.charAt(j));
		}
		System.out.println(sb.toString());
		if(temp.compareTo(sb.toString())==0)
		return true;
		else return false;
	}

}
