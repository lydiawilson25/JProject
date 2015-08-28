package codebyter;

import java.util.*; 
import java.io.*;

public class Decibina {  
  public static String BinaryConverter(String str) { 
  
    int bina = Integer.parseInt(str);
    int deci=0;
    for(int i=0;i<str.length();i++){
     deci=deci+bina%10 * (int)(Math.pow(2,i)); 
     //System.out.print((bina%10)+":"+(Math.pow(2,i))+"//");
     bina=bina/10;
        
    }
    str=Integer.toString(deci);
    return str;
    
  } 
  
  public static void main (String[] args) {  
    // keep this function call here     
    //Scanner  s = new Scanner(System.in);
    //Function c = new Function();
    System.out.print(BinaryConverter("100101")); 
  }   
  
}








  