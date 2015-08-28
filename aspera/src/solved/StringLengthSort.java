package solved;

import java.util.Comparator;

public class StringLengthSort implements Comparator<String>
	{
	public int compare(String s1, String s2)
	{
		
		if (s1.length() < s2.length())
			return 1;
		else if (s1.length() > s2.length())
			return -1;
		else // for both string of equal length
			return 0;
	}
}