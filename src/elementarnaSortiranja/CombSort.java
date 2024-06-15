package elementarnaSortiranja;

public class CombSort {
    private static int nextGap(int k) {
        k /= 1.3;
        if (k == 9 || k == 10) k = 11;
        else if (k < 1) k = 1;
        return k;
    }
    public static <T extends Comparable<T>> void CombSort(T[] arr) {
        boolean sorted = false;
        int k = arr.length;

        do {
            k = nextGap(k);

            boolean swaped = false;
            for (int i = 0; i < arr.length - k; i++) {
                if (arr[i].compareTo(arr[i + k]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + k];
                    arr[i + k] = temp;
                    swaped = true;
                }
            }
            sorted = (k == 1 && !swaped);
        } while (!sorted);
    }
}
