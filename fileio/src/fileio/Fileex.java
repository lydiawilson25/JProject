package fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fileex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "C:/Users/Wilson/workspace/fileio/src/fileio/test.txt";
		String line;
		try {
			FileReader 	filereader = new FileReader(filename);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			while((line=bufferedreader.readLine())!=null){
				System.out.println(line);
			}
			bufferedreader.close();
		}
		catch(FileNotFoundException ex){
			System.out.println("unable to find the file: "+filename);
		}
		catch(IOException ex){
			ex.printStackTrace();
		}

	}

}
