package codewars;
import java.util.ArrayList;

/**
 * Created by Wilson on 8/27/2015.
 */
public class Biblescripture {

    public static void main(String[] args){
        String[] scripture = {"01001001", "01001002", "01002001", "01002002", "01002003", "02001001", "02001002", "02001003"};

                String [] temp = filterBible(scripture, "01", "001");
        for(String s: temp)
            System.out.println(s);


    }
    public static String[] filterBible(String[] scripture, String book, String chapter) {
        String bb=null,ccc=null,vvv=null;
        ArrayList<String> temp = new ArrayList<String>();
        for(String s:scripture) {
            bb = s.substring(0,2);

            ccc=s.substring(2,5);
            vvv=s.substring(5,8);
            int inbb = Integer.parseInt(bb);
            int inccc = Integer.parseInt(ccc);
            int invvv = Integer.parseInt(vvv);

            if(1<inbb || inbb<=66 && 1<=inccc && 1<=invvv){
                if(bb.equals(book) && ccc.equals(chapter)){
                    temp.add(s);
                }
            }


        }
        String[] sarr = temp.toArray(new String[temp.size()]);

return sarr;
    }
}
