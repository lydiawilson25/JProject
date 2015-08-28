package aspera;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Longestword implements Comparator<String> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "C:/Users/Wilson/workspace/aspera/src/aspera/test.txt";
		readfile(filename);

	}

	public int compare(String o1, String o2) {
		return Integer.compare(o1.length(), o2.length());
	}

	public static void readfile(String filename) {
		String line;
		Set<String> sarr = new HashSet<String>();
		
		
		try {
			FileReader filereader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			while ((line = bufferedreader.readLine()) != null) {
				sarr.add(line);
				
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (String s : sarr) {
			System.out.println(s);
		}
		//sortfile(sarr);	
		callwordbreak(sarr);
		

	}

	public static void callwordbreak(Set<String> sarr){
		int len=0,max=0;
		String maxword=null;

			for(String s: sarr){
		if(wordbreak(s,sarr)==true){
			len = s.length();
			if(len>max){
				max=len;
				maxword=s;
			}
			
		}
	}
	System.out.println("the word is: "+maxword);

	}

	public static void sortfile(Set<String> sarr) {
		String[] strarray = new String[sarr.size()];
		sarr.toArray(strarray);
		Arrays.sort(strarray, new Longestword());
		System.out.println("\nsorted:");
		for (String s : strarray) {
			System.out.println(s);
		}
		selectword(strarray);

	}
	
	public static void selectword(String[] strarray){
		String curword=null,maxword=null,preword=null;
		int count=0;
		for(int i=strarray.length-1;i>0;i--){
			curword=strarray[i];
			for(int j=i-1;j>0;j--){
				preword=strarray[i];
			if(curword.contains(preword)){
				curword=curword.replace(preword, "");
				count++;
				if(curword==null)
					maxword=curword;
			}
		
			}
		}
	}

	public static void findword(String[] strarray) {
		ArrayList<String> strlist = new ArrayList<String>(Arrays.asList(strarray));
		int arrlen = strarray.length;
		String originalstr = strarray[arrlen-3];
		System.out.println("longest word: "+originalstr);
		int count = 0;
		int len = originalstr.length();
		String temp=originalstr;
		int i = 0;
		for (int j = len; j > 1; j--) {
			while (temp != null) {
				String cursub = temp.substring(i, j);
				if(cursub==originalstr)
					break;
				// System.out.println("i: "+i+" j: "+j);
				// System.out.println(cursub);
				if (strlist.contains(cursub)) {
					 System.out.println("current substring: " + temp);
					 temp = temp.replace(cursub, "");
					count++;
					 System.out.println("current substring after replace: " + temp);
					j = temp.length();
					if (j == 0)
						break;
				} else
					break;
			}
		}
		System.out.println("no of words: " + count);
	}
	
	public static boolean wordbreak(String input, Set<String> dict) {
		
		boolean[] t = new boolean[input.length() + 1];
		t[0] = true;
		for (int i = 0; i < input.length(); i++) {
			if (!t[i])
				continue;
			for (String a : dict) {
				int len = a.length();
				int end = i + len;
				if (end > input.length())
					continue;
				if (t[end])
					continue;
				if (input.substring(i, end).equals(a)) {
					t[end] = true;
				}
			}
		}
		return t[input.length()];
	}

}
