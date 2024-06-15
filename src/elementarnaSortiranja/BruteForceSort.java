package elementarnaSortiranja;

public class BruteForceSort {
    public static <T extends Comparable<T>> void bruteForceSort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i].compareTo(a[j]) > 0) {
                    T tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }
}
