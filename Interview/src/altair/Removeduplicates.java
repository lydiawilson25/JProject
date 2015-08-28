package altair;

public class Removeduplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "college";
		removeDuplicates(str);
	}

	public static void removeDuplicates(String str1) {
		char[] str=str1.toCharArray();
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;
		int tail = 1;
		for (int i = 1; i < len; ++i) {
			int j;
			for (j = 0; j < tail; ++j) {
				if (str[i] == str[j])
					break;
			}
			if (j == tail) {
				str[tail] = str[i];
				++tail;
			}
		}
		str[tail] = 0;
		System.out.println(str);
	}
}
