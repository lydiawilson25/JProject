/* Sample code to read in test cases:*/
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Unique {
    public static void main(String[] args) throws IOException {
        String filename = "C:/Users/Wilson/workspace/codeval/src/unique.txt";
        File file = new File(filename);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;

        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            if(line.length()>0)
                findunique(line);
        }
    }

    public static void findunique(String str) {
        String[] sarr = str.replaceAll("\\s","").split(",");
        Set<Integer> hset = new HashSet<Integer>();
        for (int i = 0; i < sarr.length; i++) {
            hset.add(Integer.parseInt(sarr[i]));
        }
int count=1;

            Iterator it = hset.iterator();
            while (it.hasNext()) {
                System.out.print(it.next());
                count++;
                if(count<=hset.size()){
                    System.out.print(",");
                }

            }
        System.out.println();



        }
    }

