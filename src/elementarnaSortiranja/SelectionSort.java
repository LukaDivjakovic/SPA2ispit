package elementarnaSortiranja;

public class SelectionSort {
    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {

//            trazimo minimum u podnizu [i .. arr.length - 1]
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

//            minimum ide na pocetak podniza [i .. arr.length - 1]
            if (minIndex != i) {
                T temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
