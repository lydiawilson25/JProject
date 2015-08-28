package codebyter;

import java.util.*; 
import java.io.*;

public class Arradd {  
  public static String ArrayAddition(int[] arr) { 
  if(arr==null)
    return "false";
    Arrays.sort(arr);
    int len = arr.length;
    int max = arr[len-1];
    int sum=0;
    for(int i=0;i<len-1;i++){
      sum=sum+arr[i];
    }
      System.out.println(max);
    if(sum>max)
      return "true";
      else return "false";   
  } 
  
  public static void main (String[] args) {  
    // keep this function call here     
   // Scanner  s = new Scanner(System.in);
    //Function c = new Function();
      int[] arr={1,2,3,4};
    System.out.print(ArrayAddition(arr)); 
  }   
  
}








  