package hashtable;

import java.util.Enumeration;
import java.util.Hashtable;

public class Simplehashtable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable companies = new Hashtable();
		companies.put("Google", "USA");
		companies.put("Nokia", "Finland");
		companies.put("Sony", "Japan");
		System.out.println("The value for the key google is: " + companies.get("Google"));
		System.out.println("Does the hashtable contain the key google: " + companies.containsKey("Google"));
		System.out.println("Does the hashtable contain the value japan: " + companies.containsValue("Japan"));
		Enumeration enumeration = companies.elements();
		while (enumeration.hasMoreElements()) {
			System.out.println("hashtable values: " + enumeration.nextElement());
		}

	}

}
