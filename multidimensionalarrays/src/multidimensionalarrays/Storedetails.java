package multidimensionalarrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Storedetails {

	public static void main(String[] args) throws Exception {
		long itemID = 0;
		double price = 0.00d;
		boolean itemFound = false;
		String description = "Unknown";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// The first list contains women's items
		// The other contains non-women items
		long[][] itemNumbers = new long[][] { { 947783, 934687, 973947, 987598, 974937 },
				{ 739579, 367583, 743937, 437657, 467945 } };

		String[][] itemNames = new String[][] {
				{ "Women Double-faced wool coat", "Women Floral Silk Tank Blouse", "Women Push Up Bra",
						"Women Chiffon Blouse", "Women Bow Belt Skirtsuit" },
				{ "Men Cotton Polo Shirt", "Children Cable-knit Sweater  ", "Children Bear Coverall Cotton",
						"Baby three-piece Set         ", "Girls Jeans with Heart Belt  " } };

		double[][] unitPrices = new double[][] { { 275.25D, 180.05D, 50.00D, 265.35D, 245.55D },
				{ 45.55D, 25.65D, 28.25D, 48.55D, 19.95D } };

		// Order Processing
		try {
			System.out.print("Enter Item Number: ");
			itemID = Long.parseLong(br.readLine());
		} catch (InputMismatchException ime) {
			System.out.println("Invalid Number - The program will terminate");
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				if (itemID == itemNumbers[i][j]) {
					description = itemNames[i][j];
					price = unitPrices[i][j];
					itemFound = true;
				}
			}
		}

		if (itemFound == true) {
			System.out.println("Item Identification");
			System.out.println("Item Number: " + itemID);
			System.out.println("Description: " + description);
			System.out.printf("Unit Price:  %.2f\n", price);
		} else
			System.out.println("No item with that number was found.");

	}
}