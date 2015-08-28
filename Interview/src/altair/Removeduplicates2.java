package altair;

public class Removeduplicates2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="college";
		removedup(str);
	}
	@SuppressWarnings("null")
	public static void removedup(String str){
		int i,j,k=0;
		char[] arr=str.toCharArray();
		System.out.println(arr);
		int len = arr.length;
		//char arr2[]=new char[len];
				
		for(i=0;i<len;i++){
			for(j=i+1;j<len;j++){				
				if(arr[i]==arr[j]){
					arr[j]=' ';				
				}
			}
		}
		System.out.println(arr);
	}
}
				