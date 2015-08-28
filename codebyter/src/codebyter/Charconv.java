package codebyter;

import java.util.*;
import java.io.*;

public class Charconv {
    public static String CaesarCipher(String str, int num) {
        char[] carr = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c:carr){
            if(Character.isLetter(c)) {
                char newc = (char) ((int) c + num);
                sb.append(newc);
            }
            else if(c==' ')
                sb.append(c);
        }
        str=sb.toString();
        return str;
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner  s = new Scanner(System.in);
        //Function c = new Function();
        //System.out.print(c.CaesarCipher(s.nextLine()));
        String str =  "world!";
        System.out.println(CaesarCipher(str,2));
    }

}








  