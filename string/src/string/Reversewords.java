package string;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Reversewords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//readinput();
		/*
		 * String filename =
		 * "C:/Users/Wilson/workspace/string/src/string/temp.txt"; String[]
		 * strarray = { "This is test", "Hello World" }; printit(strarray);
		 * reverseit(strarray, 2);
		 */
		String filename = "C:/Users/Wilson/workspace/string/src/string/temp.txt";
		readfile(filename);
	}

	public static void readfile(String filename) {		
		String line = null;
		ArrayList<String> strlist =new ArrayList<String>();
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			line = br.readLine();
			while (line != null) {				
				strlist.add(line);
				System.out.println(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		} catch (IOException io) {
			System.out.println("Unable to open file");
			io.printStackTrace();
		}
		String[] strarray = new String[strlist.size()];
		strarray = strlist.toArray(strarray);
		reverseit(strarray,strarray.length);
	}

	public static void readinput() {
		int i;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of test cases: ");		
		int num = in.nextInt();
		String[] strarray = new String[num];		
		in.nextLine();		
		System.out.print("Enter the string");
		for (i = 0; i < num; i++) {
			strarray[i] = in.nextLine();
		}
		printit(strarray);
		reverseit(strarray,num);
	}

	public static void reverseit(String[] strarray, int num) {
		int i;
		String[] rev = new String[num];
		for (i = 0; i < num; i++) {
			String temp = strarray[i];
			StringBuilder sb = new StringBuilder();
			String[] words = temp.split(" ");
			for (int j = words.length - 1; j >= 0; j--) {
				sb.append(words[j]).append(" ");
			}
			temp = sb.toString();
			rev[i] = sb.toString();
		}
		printit(rev);
	}

	public static void printit(String[] strarray) {
		System.out.println("\nThe array is: ");
		for (int i = 0; i < strarray.length; i++) {
			System.out.println(strarray[i] + " ");
			
		}
	}
}
