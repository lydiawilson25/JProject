package codebyter;

import java.util.ArrayList;

/**
 * Created by Wilson on 8/21/2015.
 */
public class Checkprime {

    public static void main(String[] args){
        int num1 = 240;
        System.out.println(findPrime(num1));
        //permutation("", "ABC");
    }


    public static int findPrime(int num1){

       // ArrayList<String> s1 = new ArrayList<String>();
        String[] s1=permutation("",Integer.toString(num1));
        for(int i=0;i<s1.length;i++){
            System.out.println(s1[i]);
        }
        ArrayList<Integer> numarr = new ArrayList<Integer>();
        for(String s: s1) {
            numarr.add(Integer.parseInt((s)));
            System.out.println(s);
        }
        for(int i=0;i<numarr.size();i++){
            System.out.println(numarr.get(i));
            if(isPrime(numarr.get(i))) {
                return 0;
            }
        }
        return 1;

    }
    public static boolean isPrime(int num){
        for(int i=2;i<=num/2;i++){
            if(num%i==0)
                return false;
        }
        return true;
    }

    private static String[] permutation(String prefix, String str) {
        int n = str.length();
        ArrayList<String> sarr = new ArrayList<String>();
        if (n == 0)
            sarr.add(prefix);
        else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
        //for(String s: sarr)
        //    System.out.println(s);
        String[] sarr1 = sarr.toArray(new String[sarr.size()]);
        return sarr1;


    }
}
