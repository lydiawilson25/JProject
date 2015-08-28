package algorithms;

public class stringtointeger {

	public static void main(String[] args) {
		
		String str1 = "5678";
		int int1;
		int1 = convert(str1);
		System.out.println("The integer is : " +int1);

	}

	public static int convert(String str) {
		if (str == null || str.length() < 1)
			return 5;

		str = str.trim();
		char flag = '+';
		int i = 0;

		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}

		double result = 0;

		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			i++;
		}
		
		if(flag=='-'){
			result = -result;
		}
		
		if(result>Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if(result<Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return (int) result;

	}

}
