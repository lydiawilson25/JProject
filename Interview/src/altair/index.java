package altair;

public class index {
	public static void main(String args[]) {
		int arr[] ={-3, -1, 1, 3, 5};
		int number=getNumberSameAsIndex(arr);
		System.out.println("value of array=");
		for(int i=0;i<arr.length;i++) {
			System.out.print("  "+arr[i]);
		}
		System.out.println("\nThe number is=" +number);
		
	}
	 public static int getNumberSameAsIndex(int[] numbers) {
		    if(numbers == null || numbers.length == 0) {
		        return -1;
		    }
		       
		    int leftindex = 0;
		    int rightindex = numbers.length - 1;
		    while(leftindex <= rightindex) {
		        int middleindex = leftindex + ((rightindex - leftindex) >>> 1);
		        System.out.println("value of left="+leftindex+"  value of right="+rightindex+"  value of middle="+middleindex);
		        if(numbers[middleindex] == middleindex) {
		            return middleindex;
		        }
		           
		        if(numbers[middleindex] > middleindex) {
		            rightindex = middleindex - 1;
		        }
		        else {
		            leftindex = middleindex + 1;
		        }
		    }
		       
		    return -1;
		}

}
