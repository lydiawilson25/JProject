package numbers;
import java.util.*; 
import java.io.*;

public class Findfact {  
  static int FirstFactorial(int num) { 
  
    // code goes here   
    /* Note: In Java the return type of a function and the 
       parameter types being passed are defined, so this return 
       call must match the return type of the function.
       You are free to modify the return type. */
    int fact = 1;
    if(num>0 && num<19){
      for(int i=1;i<=num;i++){
    	  fact = fact * i;
      }
      System.out.println(fact);
      return fact;
    }
    else{
    	System.out.println(-1);
    	return -1;
    }
     
     
    
  } 
  
  public static void main (String[] args) {  
    // keep this function call here     
    FirstFactorial(8);
  }   
  
}








  