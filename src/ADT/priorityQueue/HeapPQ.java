package ADT.priorityQueue;

import java.util.ArrayList;

public class HeapPQ<T extends Comparable<T>> implements PriorityQueue<T> {
    private static final int DEFAULT_INITAL_CAPASITY = 100;

    private ArrayList<T> queue;

    public HeapPQ(int capasity) {
        if (capasity <= 0) {
            throw new IllegalArgumentException("Capasity must be a positive integer");
        }
        queue = new ArrayList<>(capasity);
    }

    public HeapPQ() {
        this(DEFAULT_INITAL_CAPASITY);
    }

    private void restoreHeapProperty(int sonIndex) {
        int parentIndex = (sonIndex - 1) / 2;
        boolean heapRestored = false;
        while (!heapRestored && sonIndex > 0) {
            T parent = queue.get(parentIndex);
            T child = queue.get(sonIndex);

            if (child.compareTo(parent) < 0) {
                queue.set(parentIndex, child);
                queue.set(sonIndex, parent);
                sonIndex = parentIndex;
                parentIndex = (sonIndex - 1) / 2;
            } else {
                heapRestored = true;
            }
        }

    }

    @Override
    public void insert(T element) {
        queue.add(element);
        restoreHeapProperty(queue.size() - 1);
    }

    @Override
    public T max() {
        return queue.getFirst();
    }

    public void reverseRestoreHeapProperty(int parentIndex) {
        boolean heapRestored = false;
        while (!heapRestored) {
            int maxSonIndex = (queue.get(parentIndex * 2 + 1).compareTo(queue.get(parentIndex * 2 + 2)) > 0) ? parentIndex * 2 + 1 : parentIndex * 2 + 2;
            T parent = queue.get(parentIndex);
            T child = queue.get(maxSonIndex);
            if (parent.compareTo(child) < 0) {
                queue.set(parentIndex, child);
                queue.set(maxSonIndex, parent);
                parentIndex = maxSonIndex;
            } else {
                heapRestored = true;
            }
        }
    }

    @Override
    public T delMax() {
        T temp = max();
        queue.set(0, queue.getLast());
        queue.set(queue.size() - 1, temp);
        queue.removeLast();
        reverseRestoreHeapProperty(0);
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return queue.size() == 0;
    }

    @Override
    public int size() {
        return queue.size();
    }
}
