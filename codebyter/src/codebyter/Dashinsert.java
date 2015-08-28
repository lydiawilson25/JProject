package codebyter;

/**
 * Created by Wilson on 8/22/2015.
 */
public class Dashinsert {

    public static void main(String[] args){
        String str = "4546793";
        insertdash(str);
    }

    public static void insertdash(String str){
        StringBuilder sb = new StringBuilder(str);
        int len=sb.length();
        for(int i=0;i<len-1;i++){
            if(Character.isDigit(sb.charAt(i))) {
                int num1 = Integer.parseInt(sb.charAt(i) + "");
                int num2 = Integer.parseInt(sb.charAt(i + 1) + "");
                if (isOdd(num1) && isOdd(num2)) {
                    sb.insert(i + 1, '-');
                    len++;
                }
                else if (isEven(num1) && isEven(num2)) {
                    sb.insert(i + 1, '*');
                    len++;
                }
            }
        }
        System.out.println(sb.toString());

    }

    public static boolean isOdd(int num){

        if(num%2==0 || num==0)
                return false;

        return true;
    }

    public static boolean isEven(int num){
        if(num%2==1 || num==0)
            return false;

        return true;

    }
}
