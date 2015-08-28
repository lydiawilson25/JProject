package codebyter;

public class Arithmorgeo {

    public static String ArithGeo(int[] arr) {
        if (isArith(arr)) {
            return "Arithmetic";
        } else if (isGeo(arr)) {
            return "Geometric";
        } else {
            return "-1";
        }
    }

    public static boolean isArith(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) != Math.abs(arr[i + 1] - arr[i + 2])) {
                return false;
            }
        }
        return true;

    }

    public static boolean isGeo(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {            
            if (arr[i+1]/arr[i] != arr[i + 2]/arr[i + 1]) {
                return false;

            }
        }
        return true;

    }

    public static void main(String[] args) {
        // keep this function call here
        // Scanner s = new Scanner(System.in);
        // Arithmorgeo c = new Arithmorgeo();
        // System.out.print(c.ArithGeo(s.nextLine()));
        int[] arr = {5, 10, 15}; // arith
        int[] arr1 = {2, 4, 16, 24}; // -1
        int[] arr2 = {2, 6, 18, 54}; // geo

        System.out.println(ArithGeo(arr1));
    }

}
