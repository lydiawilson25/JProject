/**
 * Created by Wilson on 8/27/2015.
 */
public class Oddneven {
    public static void main(String[] args){
        String input = "74";
        System.out.println(sumofdigits(input));
    }

    public static int sumofdigits(String input) {
        int sumofodds=0,sumofeven=0,diff;
        for(int i=0;i<input.length();i++){
            int num = Character.getNumericValue(input.charAt(i));
            if(num%2==0)
                sumofeven=sumofeven+num;
            else
                sumofodds=sumofodds+num;
        }
        diff = Math.abs(sumofeven-sumofodds);
        return diff;
    }
}
