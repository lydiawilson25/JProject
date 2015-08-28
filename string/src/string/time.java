package string;
import java.util.*; 
import java.io.*;

public class time {  
  static String TimeConvert(int num) { 
  
    // code goes here   
    /* Note: In Java the return type of a function and the 
       parameter types being passed are defined, so this return 
       call must match the return type of the function.
       You are free to modify the return type. */
    int hours = num/60;
    int min = num%60;
    String str = hours + ":" +min;
          System.out.println(str);
    return str;
    
  } 
  
  public static void main (String[] args) {  
    // keep this function call here     
    TimeConvert(126); 
  }   
  
}








  