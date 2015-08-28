package string;

import java.util.Arrays;
import java.util.Comparator;

public class Stringlength implements Comparator<String> {
	  public int compare(String o1, String o2) {
		    return Integer.compare(o1.length(), o2.length());
		  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] sarr = {"hippopotamus","cat","dogs","sheep"};
		Arrays.sort(sarr, new Stringlength());
		for(int i=0;i<sarr.length;i++){
			System.out.println(sarr[i]);
		}

	}

}
