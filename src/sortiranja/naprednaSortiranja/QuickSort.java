package sortiranja.naprednaSortiranja;

public class QuickSort {
//    kada je duzina niza < 17 koristimo elementarna sortiranja, npr izbiranje
//    quicksort radi gore kada je niz delimicno slozen, najbolje je da ga izmesamo pre
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
        if (left < right) {
            int j = partitionHoare(arr, left, right);
            quickSort(arr, left, j - 1);
            quickSort(arr, j + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partitionHoare(T[] arr, int left, int right) {
        T pivot = arr[left];
        int i = left + 1;
        int j = right;

        while (i <= j) {
            while (i <= right && arr[i].compareTo(pivot) < 0) i++;
            while (j >= left + 1 && arr[j].compareTo(pivot) > 0) j--;

            if (i <= j) {
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        T temp = arr[left];
        arr[left] = arr[j];
        arr[j] = temp;

        return j;
    }

    private static <T extends Comparable<T>> int partitionLouto(T[] arr, int left, int right) {
        T pivot = arr[right];
        int ltePovot = left - 1;

        for (int i = left; i < right; i++) {
            if (arr[i].compareTo(pivot) <= 0) {
                ++ltePovot;
                T temp = arr[ltePovot];
                arr[ltePovot] = arr[i];
                arr[i] = temp;
            }
        }

        int placeForPivot = ltePovot + 1;

        T temp = arr[placeForPivot];
        arr[placeForPivot] = arr[right];
        arr[right] = temp;

        return placeForPivot;
    }
}
