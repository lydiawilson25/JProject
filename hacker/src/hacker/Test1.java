package hacker;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="07:05:45PM";
		str=str.replace("PM", "");
		System.out.println(str);
		String[] arr = str.split(":");
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
			//System.out.println(str.contains("PM"));
		}

	}

}
