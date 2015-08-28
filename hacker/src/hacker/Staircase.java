package hacker;

public class Staircase {
	public static void drawstair(int n){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if((i+j)>=n-1)
				System.out.print("#");
				else
					System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
drawstair(3);
	}

}
