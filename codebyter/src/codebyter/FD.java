package codebyter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class FD {
    public static float FormattedDivision(float num1, float num2) {

        // code goes here
    /* Note: In Java the return type of a function and the 
       parameter types being passed are defined, so this return 
       call must match the return type of the function.
       You are free to modify the return type. */
        float ans = (float) (num1/num2);
        //if


        return ans;

    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner  s = new Scanner(System.in);
       // Function c = new Function();
        float num1 = (float)123456789 ;
        float num2 = (float)10000 ;
        DecimalFormat df = new DecimalFormat();
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.print(df.format(FormattedDivision(num1,num2)));
    }

}








  