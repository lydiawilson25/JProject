package hacker;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Wilson on 8/21/2015.
 */
public class Add {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        ArrayList<Integer> numarray = new ArrayList<Integer>();
        while(in.hasNext()){
            numarray.add(in.nextInt());
        }
        int sum = 0;
        for(int i : numarray){
            sum = sum + i;
        }
        System.out.println(sum);
    }
}
