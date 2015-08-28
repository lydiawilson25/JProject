package codebyter;
import java.util.*; 
import java.io.*;

public class maxcharcount {  
  public static String LetterCountI(String str) {   
   //sentence->string[]->each word->count maxchar->store maxcount and maxword
    String[] words = str.split(" ");
    int curmax=-1, max=-1;
    String maxword="-1";
    for(int i=0;i<words.length;i++){
      curmax=countmax(words[i]);
      if(curmax>max){
        max=curmax;
        maxword=words[i];
      }      
    }       
    return maxword;    
  } 
  
  public static int countmax(String str){
   char[] carr=str.toCharArray();
    Arrays.sort(carr);
    int curcount=0,maxcount=0;
    for(int i=0;i<carr.length-1;i++){
      if(carr[i]==carr[i+1]){
         curcount=curcount+1;
      }
      else{
       if(curcount>maxcount)
       {
         maxcount=curcount;         
       }
        curcount=0;
      }
    }
    return maxcount;
  }
  
  public static void main (String[] args) {  
    // keep this function call here     
    //Scanner  s = new Scanner(System.in);
    //Function c = new Function();
      String str = "Hello apple pie";
    System.out.print(LetterCountI(str)); 
  }   
  
}








  