package codebyter;

import java.util.HashMap;

/**
 * Created by Wilson on 8/21/2015.
 */
public class Meanmodeusehashmap {
    public static void main(String[] args){
        int[] numarray = {10,10};
        System.out.println(findmeanmode(numarray));
    }

    public static int findmeanmode(int[] numarray){
        int ans=0;
        if(findmean(numarray)==findmode(numarray))
            ans=1;
        return ans;
    }

    public static int findmean(int[] numarray){
        int len=numarray.length;
        int sum=0;
        for(int i:numarray){
            sum=sum+i;
        }
        return (sum/len);
    }

    public static int findmode(int[] numarray){
     HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        int maxcount=-1,maxnum=-1;
       for(int i:numarray){
           if(hm.containsKey(i)){
               hm.put(i,hm.get(i)+1);
               if(hm.get(i)+1>maxcount){
                   maxcount=hm.get(i);
                   maxnum=i;
               }
           }
           else {
               hm.put(i,0);
           }
       }
        return maxnum;

    }



}
