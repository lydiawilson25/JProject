package altair;

public class Uniquestring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "LydLa";
		boolean ans = isitunique(str);
		System.out.println(ans);

	}
	
	public static boolean isitunique(String str) {
		boolean charpresent[] = new boolean[256];
		for(int i=0;i<str.length();i++){
			int val = str.charAt(i);
			if(charpresent[val]) return false;
			charpresent[val]=true;
		}
		return true;
	}

}
