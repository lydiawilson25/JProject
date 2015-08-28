package string;

import java.util.*; 
import java.io.*;

public class LetterChanges2 {  
  static String LetterChanges(String str) { 
  
   char[] cc = str.toCharArray();
    for(int i=0; i<cc.length; i++) {
      if(Character.isLetter(cc[i])) {
        if(cc[i] == 'z')	cc[i] = 'a';
        else if(cc[i] == 'Z') cc[i] = 'A';
        else {
          cc[i]++;
        }
        
        if(cc[i]=='a' || cc[i]=='e' || cc[i]=='i' || cc[i]=='o' || cc[i]=='u') 
          cc[i] -= 32;
      }
    }
    System.out.println(cc);
    return new String(cc);
    
  } 
  
  public static void main (String[] args) {  
    // keep this function call here     
    LetterChanges("code!"); 
  }   
  
}
