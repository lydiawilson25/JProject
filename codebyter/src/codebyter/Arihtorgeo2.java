package codebyter;

import java.util.*;
import java.io.*;

public class Arihtorgeo2 {

    public static String ArithGeoII(int[] arr) {
        if (isArith(arr)) {
            return "Arith";
        } else if (isGeo(arr)) {
            return "Geo";
        } else {
            return "-1";
        }
    }

    public static boolean isArith(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if ((arr[i + 1] - arr[i]) != (arr[i + 2] - arr[i + 1])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGeo(int[] arr) {
        float[] arr5 = new float[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr5[i] = (float) arr[i];
        }
        for (int i = 0; i < arr5.length - 2; i++) {
            if ((arr5[i + 1] / arr5[i]) != (arr5[i + 2] / arr5[i+1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // keep this function call here     
        //Scanner  s = new Scanner(System.in);
        //Function c = new Function();
        //System.out.print(c.ArithGeoII(s.nextLine())); 
        int[] arr = {5, 10, 15, 20}; // arith
        int[] arr1 = {2, 4, 16, 24}; // -1
        int[] arr2 = {2, 6, 18, 54}; // geo
        float[] arr4 = new float[arr2.length];
        for (int i = 0; i < arr2.length; i++) {
            arr4[i] = (float) arr2[i];
        }
        for (int i = 0; i < arr.length - 2; i++) {
            System.out.println((arr4[i + 2] / arr4[i + 1] != arr4[i + 1] / arr4[i]));
        }
        System.out.println(ArithGeoII(arr2));
    }

}
