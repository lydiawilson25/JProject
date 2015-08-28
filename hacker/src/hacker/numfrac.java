package hacker;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class numfrac {
    
    public static void findfrac(int[] arr){
        int pos=0,neg=0,zer=0;
        int len=arr.length;
        for(int i=0;i<len;i++){
            if(arr[i]==0)
                zer++;
            else if(arr[i]>0)
                pos++;
                else neg++;
        }
        float numpos=pos/len,numneg=neg/len,numzer=zer/len;
        System.out.println((float)(3/6));
        System.out.println(neg+"/"+len);
        System.out.println(zer+"/"+len);
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	System.out.println("enter");
        Scanner in=new Scanner(System.in);
        int len;
        len=in.nextInt();
        int[] arr=new int[len];
        for(int i=0;i<len;i++){
            arr[i]=in.nextInt();
        }
        findfrac(arr);
    }
}