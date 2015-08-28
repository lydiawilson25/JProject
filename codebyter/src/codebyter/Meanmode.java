package codebyter;

/**
 * Created by Wilson on 8/21/2015.
 */
public class Meanmode {
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
        int curcount=-1,maxcount=-1,maxnum=-1;
       for(int i=0;i<numarray.length;i++){
           for(int j=i+1;j<numarray.length;j++){
               if(numarray[i]==numarray[j]) {
                   curcount++;
                   if(curcount>maxcount){
                       maxcount=curcount;
                       maxnum=numarray[i];
                   }
               }
               else{
                   if(curcount>maxcount){
                       maxcount=curcount;
                       maxnum=numarray[i];
                       curcount=0;
                   }
               }
           }
       }
        System.out.println(maxnum);
        return maxnum;
    }
}
