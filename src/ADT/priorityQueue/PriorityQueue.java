package ADT.priorityQueue;

public interface PriorityQueue<T extends Comparable<T>> {
    void insert(T element);
    T max();
    T delMax();
    boolean isEmpty();
    int size();
}
