/* Sample code to read in test cases:*/
import java.io.*;
public class Sumofnumbers {
    public static void main (String[] args) throws IOException {
        String filename = "C:/Users/Wilson/workspace/codeval/src/Sumofnumbers.txt";
        File file = new File(filename);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;

        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            findsum(line);
        }
    }

    public static void findsum(String str){
        int num = Integer.parseInt(str);
        int sum =0;
        while(num!=0){
            sum = sum + num%10;
            num=num/10;
        }
        System.out.println(sum);
        sum=0;
    }
}
