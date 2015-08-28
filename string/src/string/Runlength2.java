package string;

public class Runlength2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "wwww";
		runlength(str);

	}

	
	public static void runlength(String str) {
		char[] carr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int count = 1;
		for (int i = 0; i < carr.length; i++) {
			if (i == carr.length-1) {
				sb.append(count);
				sb.append(carr[i]);
			} else if (carr[i] == carr[i + 1]) {
				count++;
			} else {
				sb.append(count);
				sb.append(carr[i]);
				count = 1;
			}
		}
		System.out.println(sb.toString());
	}

}
