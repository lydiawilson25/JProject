package codewars;
/**
 * Created by Wilson on 8/27/2015.
 */
public class WaterVolume {
    public static void main(String[] args){
        int[] arr = {2,0,3,0,4,0,1};
        System.out.println(findvolume(arr));
    }

    public static int findvolume(int[] arr){
    	
    	int[] temp = {2,0,3,0,4,0,1};
        int len = temp.length, vol=0;
        for(int i=0;i<len-2;i++){
           
                System.out.println(temp[i]);
                System.out.println("next:"+temp[i+1]);
                System.out.println(Math.min(temp[i], temp[i+2]));
        }
        return vol;
        

    }
}