package arrays;

public class Missingonenum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr={1,2,3,5,6};
		int count = 6;
		int missnum=printmissingnum(arr,count);
		System.out.println("The missing number: "+missnum);
	}
	
	public static int printmissingnum(int[] arr,int count){
		int sum = count*(count+1)/2;
		int i,actualsum=0;
		for(i=0;i<arr.length;i++){
			actualsum = actualsum + arr[i];
		}
		int missingnum = sum-actualsum;
		return missingnum;
	}

}
