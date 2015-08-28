/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codebyter;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author Wilson
 */
public class greatest {

    public static String greatestword(String str) {
        String[] words = str.split("\\s");
        int maxcount = 0, curcount = 0;
        String maxword = null;
        for (int i = 0; i < words.length; i++) {
            curcount = maxchar(words[i].toLowerCase());
            if (curcount > maxcount) {
                maxcount = curcount;
                maxword = words[i];
            }
        }
        return maxword;
    }

    public static int maxchar(String str) {
        char[] carr = str.toCharArray();
        Arrays.sort(carr);
        int curcount = 0, maxcount = 0;
        for (int i = 0; i < carr.length - 1; i++) {
            if (carr[i] == carr[i + 1]) {
                curcount++;
            } else {
                if (curcount > maxcount) {
                    maxcount = curcount;
                }
            }
        }
        return maxcount;

    }

    public static void main(String args[]) {
        String str = "Today is the greatest day";
        System.out.println(greatestword(str));
    }
}
