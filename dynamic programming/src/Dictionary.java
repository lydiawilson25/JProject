import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dictionary {

	public static void main(String[] args) {
		Set<String> dict = new HashSet<String>();
		dict.add("i");
		dict.add("like");
		dict.add("icecream");
		dict.add("samsung");
		dict.add("mobile");
		dict.add("ice");
		dict.add("mango");
		dict.add("man");
		dict.add("go");
		System.out.println(SegmentString("ilikeicecream", dict));
	}
	// TODO Auto-generated method stub

	public static String SegmentString(String input, Set<String> dict) {
		Map<String, String> memoized = new HashMap<String, String>();
		if (dict.contains(input))
			return input;
		if (memoized.containsKey(input)) {
			return memoized.get(input);
		}
		int len = input.length();
		for (int i = 1; i < len; i++) {
			String prefix = input.substring(0, i);
			if (dict.contains(prefix)) {
				String suffix = input.substring(i, len);
				String segSuffix = SegmentString(suffix, dict);
				if (segSuffix != null) {
					return prefix + " " + segSuffix;
				}
			}
		}
		memoized.put(input, null);
		return null;
	}

}
