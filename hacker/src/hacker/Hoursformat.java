package hacker;

/**
 * Created by Wilson on 8/21/2015.
 */
public class Hoursformat {
    public static void main(String[] srgs){
        String str="07:00:00AM";
        convert(str);
    }

    public static void convert(String str){
        String time = "AM";
        if(str.contains("PM"))
            time = "PM";
        str=str.replaceAll("[A-Z]","");
       String[] timesplit = str.split(":");
        int hour = Integer.parseInt(timesplit[0]);
        if(time=="AM" && hour==12)
           timesplit[0] = "00";
        else if(time=="PM" && hour==12)
            timesplit[0]="12";
        else if(time=="PM"){
            hour=hour+12;
            timesplit[0]=String.valueOf(hour);
        }

        StringBuilder sb = new StringBuilder();
        for(String s: timesplit) {
            sb.append(s);
            sb.append(":");
        }
        sb.replace(sb.length()-1,sb.length(),"");
        System.out.println(sb.toString());
    }
}
