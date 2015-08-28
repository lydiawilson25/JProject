public class squareroot {
	public static void main(String args[]) {
		double number = -1;
		findsquareroot(number);
	}
	public static void findsquareroot(double number) {
		boolean isPositive = true;
		double g1;
		if (number==0) System.out.println("Square root is 0");
		else if (number==1) System.out.println("Square root is 1");
		else if (number<0) {
			number = -number;
			isPositive=false;
		}
	
		double squareroot = number/2;
		do {
			g1 = squareroot;
			squareroot = (g1 + (number/g1))/2;			
		}
		while ((g1-squareroot)!=0);
		if(isPositive) {
			System.out.println("The square root of " +number+ "is: +" +squareroot+ "and -" +squareroot);
		}
		else {
			System.out.println("The square root of " +number+ "is: -" +squareroot+ "i and -" +squareroot+ "i");
		}
		
	}
	
}