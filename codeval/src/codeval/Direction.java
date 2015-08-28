package codeval;

//Sample code to read in test cases:
import java.io.*;

public class Direction {
	public static String finddirections(String[] str) {
		int[] arr = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		int maxrow = arr.length / 4;
		int currow = 0;
		int o = 0, p = 0, q = 0, r = 0;
		while (currow < maxrow) {
			arr[currow * 4 + 0] = o;
			arr[currow * 4 + 1] = p;
			arr[currow * 4 + 2] = q;
			arr[currow * 4 + 3] = r;
			currow = currow + 1;
			if (o == q && p == r)
				return "here";
			else if (o == 0 && q == 0 && r > p)
				return "N";
			else if (q > o && r > p)
				return "NE";
			else if (p == 0 && r == 0 && q > o)
				return "E";
			else if (q > o && r < p)
				return "SE";
			else if (o == 0 && q == 0 && r < p)
				return "S";
			else if (q < o && r < p)
				return "SW";
			else if (p == 0 && r == 0 && q < o)
				return "W";
			else if (q < o && r > p)
				return "NW";
			else
				return "N";			
		}
		return "false";

	}

	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/Wilson/workspace/codeval/src/codeval/input.txt");
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		String[] str=null;
		while ((line = buffer.readLine()) != null) {
			line = line.trim();
			// Process line of input Here
			str = line.split(" ");
			System.out.println(finddirections(str));
			
		}
		

	}
}
