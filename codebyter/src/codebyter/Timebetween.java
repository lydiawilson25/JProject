package codebyter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Timebetween {

    public static void main(String[] args) {
        String str = "1:00pm-11:00am";
        //System.out.println(findtime(str));
        findtime(str);
    }

    public static void findtime(String str) {
       StringTokenizer st = new StringTokenizer(str,"-");
        String[] temp = new String[2];
        String timetype1 = "pm";
        String timetype2 = "pm";
        int i=0;
        while(st.hasMoreTokens()){
            temp[i]=st.nextToken().toString();
            i++;
        }
       // for(String s: temp)
          //  System.out.println(s);
        StringBuilder t1 = new StringBuilder();
        StringBuilder t2 = new StringBuilder();
        for(char c: temp[0].toCharArray()){
            if(Character.isDigit(c))
                t1.append(c);
            else if(c=='a')
                timetype1="am";
        }
        for(char c: temp[1].toCharArray()){
            if(Character.isDigit(c))
                t2.append(c);
            else if(c=='a')
                timetype2="am";
        }
        //System.out.println(t1.toString()+" "+t2.toString());
        int time1=Integer.parseInt(t1.toString());
        int time2=Integer.parseInt(t2.toString());
        int timeleft;
        //System.out.println(t1+" "+t2);
        if(timetype1=="pm")
            time1=time1+1200;
        if(timetype2=="pm")
            time2=time2+1200;
        if(time1<time2)
            timeleft = Math.abs(time1-time2);
        else
        timeleft=(2400-time1)+time2;
        System.out.println(timeleft);
        int timelefthr,timeleftmin;
        timeleftmin = timeleft*60/100;
        System.out.println(timeleftmin);
    }

}
