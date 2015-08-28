package googleinterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Design a data structure that supports following operations in ?(1) time.

* insert(x): Inserts an item x to the data structure if not already present.

* remove(x): Removes an item x from the data structure if present.

* search(x): Searches an item x in the data structure.

* getRandom(): Returns a random element from current set of elements */

public class HashmapList {
    public static void main(String[] args) {

        HashMap<String, ArrayList<String>> degreecourse = new HashMap<String, ArrayList<String>>();
        ArrayList<String> courses1 = new ArrayList<String>();
        ArrayList<String> courses2 = new ArrayList<String>();
        courses1.add("Biology");
        courses1.add("Neurology");
        courses1.add("Phrmacology");
        courses2.add("Java");
        courses2.add("Ruby");
        courses2.add("Python");
        degreecourse.put("Doctor", courses1);
        degreecourse.put("Engineer", courses2);
        for(Map.Entry et:degreecourse.entrySet()){
            System.out.println(et.getKey()+"="+et.getValue());
        }
    }

}
