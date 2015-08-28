package string;

import java.util.*; 
import java.io.*;

public class Function {  
  String FirstReverse(String str) { 
  int i,j,len=str.length();
    char[] rev = new char[len];
    char[] strarray =new char[len];
    strarray = str.toCharArray();
    for(i=0,j=len-1;i<len;i++,j--){
     rev[i]=strarray[j];
    }
   for(char c: rev){
	   System.out.println(c);
   }
    str=new String(rev);
    System.out.println(str);
    return str;
    
  } 
  
  public static void main (String[] args) {  
    // keep this function call here     
    Scanner  s = new Scanner(System.in);
    Function c = new Function();
    String str = "coderbyte";
    System.out.print(c.FirstReverse(str)); 
  }   
  
}








  