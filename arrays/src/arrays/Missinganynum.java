package arrays;

import java.util.BitSet;

public class Missinganynum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,2,5,6};
		int count =7;
		printmissingnum(arr,count);

	}
	
	public static void printmissingnum(int[] arr, int count){
		int nummissing = count - arr.length;
		BitSet bs = new BitSet(count);
		
		for(int num:arr){
			bs.set(num-1);
		}
		int lastmissingindex=0;
		for(int i=0;i<nummissing;i++){
			lastmissingindex=bs.nextClearBit(lastmissingindex);
			System.out.println(++lastmissingindex);
		}
	}

}
