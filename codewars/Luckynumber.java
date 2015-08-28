/**
 * Created by Wilson on 8/27/2015.
 */
public class Luckynumber {
    public static void main(String[] args) {
        System.out.println(luckynum(70, 77));

    }

    public static int luckynum(int L, int R) {
        int i = L;
        while (i <= R) {
            String str = String.valueOf(i);
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != '4' && str.charAt(j) != '7') {
                    i++;
                    break;
                } else {
                    if (j == str.length() - 1 && (str.charAt(j) == '4' || str.charAt(j) == '7'))
                        return Integer.parseInt(str);
                }
            }

        }
        return -1;
    }
}
