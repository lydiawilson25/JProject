package backtrack;

import java.util.ArrayList;

public class Sortnumber {
	static int counter = 0;

	@SuppressWarnings("unchecked")
	public static boolean sortback(ArrayList<Integer> input, ArrayList<Integer> output, int level) {
		counter++;
		System.out.println("counter: " + counter + " level: " + level + " input: " + input + " output: " + output);
		int len = output.size(), num1, num2;
		if (len > 1 && output.get(len - 2) > output.get(len - 1))
			return false;
		if (input.size() == 0) {
			System.out.println("Solution: " + output);
			return true;
		}
		for (Integer n : input) {
			ArrayList clone_input = (ArrayList) input.clone();
			clone_input.remove(n);
			ArrayList clone_output = (ArrayList) output.clone();
			clone_output.add(n);
			boolean test = sortback(clone_input, clone_output, level + 1);
			if (test) {
				return true;
			}

		}
		return false;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList input = new ArrayList();
		input.add(3);
		input.add(4);
		input.add(1);
		ArrayList output = new ArrayList();
		sortback(input, output, 0);

	}

}
