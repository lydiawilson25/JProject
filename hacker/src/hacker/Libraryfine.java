package hacker;

import java.util.Scanner;

/**
 * Created by Wilson on 8/21/2015.
 */
public class Libraryfine {

    public static int calculatefine(String str1,String str2){
        String[] datesplit1 = str1.split(" ");
        String[] datesplit2 = str2.split(" ");
        int fine=0;
        int actdate = Integer.parseInt(datesplit1[0]);
        int actmonth = Integer.parseInt(datesplit1[1]);
        int actyear = Integer.parseInt(datesplit1[2]);
        int expdate = Integer.parseInt(datesplit2[0]);
        int expmonth = Integer.parseInt(datesplit2[1]);
        int expyear = Integer.parseInt(datesplit2[2]);
        if(actyear==expyear){
            if(actmonth==expmonth){
                if(actdate>expdate){
                    fine = 15 * (actdate-expdate);
                }

            }
            else if(actmonth>expmonth){
                fine = 500 * (actmonth-expmonth);
            }

        }
        else if(actyear>expyear){
            fine = 10000;
        }

        return fine;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String[] str = new String[2];
        for(int i=0;i<2;i++){
            str[i]=in.nextLine();
        }
        System.out.println(calculatefine(str[0],str[1]));
    }
}
