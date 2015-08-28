import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Wilson on 8/22/2015.
 */
public class Reversestring {

    public static void main(String[] args){
        String filename = "C:/Users/Wilson/workspace/codeval/src/input.txt";
        String line;
        ArrayList<String> str = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            while((line=br.readLine())!=null){
                line=line.trim();
                str.add(line);
            }
            br.close();
        }
        catch(IOException io){
            io.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        for(String s:str){
            sb.append(s);
        }
       // sb.replace("\n","");
        for(String s: str)
            System.out.println(s);
        String[] strarray = sb.toString().split("\n");
        for(String s:strarray){
            System.out.println(s);
        }
        System.out.println(strarray[0]);
    }






}
