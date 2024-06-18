package sortiranja.naprednaSortiranja;

public class HeapSort {
    public static <T extends Comparable<T>> int getMaxSon(T[] arr, int parentIndex, int end) {
        int son1Index = parentIndex * 2 + 1;
        int son2Index = parentIndex * 2 + 2;
        int maxSonIndex = -1;

        if (son1Index <= end) {
            maxSonIndex = son1Index;
        }
        if (son2Index <= end) {
            if (arr[son2Index].compareTo(arr[son1Index]) > 0) {
                maxSonIndex = son2Index;
            }
        }
        return maxSonIndex;
    }

    public static <T extends Comparable<T>> void makeHeap(T[] arr, int start, int end) {
        int parentIndex = start;
        boolean heapRestored = false;

        while(!heapRestored) {
            int maxSonIndex = getMaxSon(arr, parentIndex, end);
            if (maxSonIndex == -1) {
                heapRestored = true;
            } else {
                if (arr[parentIndex].compareTo(arr[maxSonIndex]) < 0) {
                    T temp = arr[parentIndex];
                    arr[parentIndex] = arr[maxSonIndex];
                    arr[maxSonIndex] = temp;
                    parentIndex = maxSonIndex;
                } else {
                    heapRestored = true;
                }
            }
        }
    }
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        int lastIndex = arr.length - 1;
        int lastParent = (lastIndex - 1) / 2;

//        napravi heap
        while (lastParent >= 0) {
            makeHeap(arr, lastParent, lastIndex);
            lastParent--;
        }

        int end = lastIndex;

        while (end > 0) {
            T temp = arr[0];
            arr[0] = arr[end];
            arr[end] = temp;

            end--;
            makeHeap(arr, 0, end);
        }
    }
}
