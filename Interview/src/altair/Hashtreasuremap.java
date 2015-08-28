package altair;

import java.util.HashMap;

public class Hashtreasuremap {
	public static void main(String args[]) {
		HashMap<String,String> treasuremap = new HashMap();
		treasuremap.put("treasure", "location=castle");
		treasuremap.put("gold", "location=island");
		System.out.println("value for the key gold=" +treasuremap.get("gold"));
		System.out.println("values of all the keys="+treasuremap.keySet());
		System.out.println("all the key value pairs="+treasuremap.entrySet());		
	}

}
