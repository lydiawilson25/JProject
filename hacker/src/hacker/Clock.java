package hacker;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Clock {
    public static void findtime(String str){
        if(str.contains("PM")){
            str=str.replace("PM","");
            String[] arr=str.split(":");
            int newhr=Integer.parseInt(arr[0])+12;
            arr[0]=String.valueOf(newhr);
            int i;
            for(i=0;i<arr.length-1;i++){
                System.out.print(arr[i]);
                System.out.print(":");
            }
            System.out.print(arr[i]);
        }
        else{
            str=str.replace("AM","");
            System.out.print(str);
        }
    }
    
    public static void pmconvert1(){
    	
    }
    


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         Scanner in=new Scanner(System.in);
        String str="07:05:45PM";
       // str=in.next();
        findtime(str);
    }
}