package sortiranja.elementarnaSortiranja;

public class InsertionSort {
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        int minIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(array[minIndex]) < 0) {
                minIndex = i;
            }
        }

        if (minIndex != 0) {
            T temp = array[minIndex];
            array[minIndex] = array[0];
            array[0] = temp;
        }

        for (int i = 2; i < array.length; i++) {
            T current = array[i];

            int j = i - 1;
            while (array[j].compareTo(current) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = current;
        }
    }
}
