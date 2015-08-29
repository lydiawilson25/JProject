package sorting;


public class Countingsort {


    public static void main(String[] args) {
        int[] array = {3, 4, 1, 2};
        int lengthofarray = array.length;
        int maxelement = 4;
        findminmax(array);


    }

    public static void findminmax(int[] array) {
        if (array.length == 0) {
            return;
        }

        // Determine minimum and maximum values
        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        sort(array, minValue, maxValue);
    }

    public static void sort(int[] array, int minValue, int maxValue) {
        int[] buckets = new int[maxValue - minValue + 1];

        for (int pos = 0; pos < array.length; pos++) {
            buckets[array[pos] - minValue]++;
        }

        int newIndex = 0;
        for (int pos = 0; pos < buckets.length; pos++) {
            while (buckets[pos] > 0) {
                array[newIndex++] = pos + minValue;
                buckets[pos]--;
            }
        }

        for (int a : array)
            System.out.println(a);
    }

}
