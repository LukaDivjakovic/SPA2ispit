package ADT.set;

import java.util.Arrays;

public class CHashSet<T> implements Set<T> {
    private static final int DEFAULT_TABLE_SIZE = 101;

    private enum Status {
        EMPTY,
        OCCUPIED,
        DELETED
    };

    private Object[] table;
    private Status[] status;
    private int numElements;

    public CHashSet() {
        this(DEFAULT_TABLE_SIZE);
    }

    public CHashSet(int tableSize) {
        if (tableSize <= 0) {
            throw new IllegalArgumentException("Table size must be greater than 0");
        }
        reset(tableSize);
    }

    private void reset(int tableSize) {
        table = new Object[tableSize];
        status = new Status[tableSize];
        Arrays.fill(status, Status.EMPTY);
        numElements = 0;
    }

    private int hash(T element) {
        return Math.abs(element.hashCode() % table.length);
    }

    private int searchCollisionChain(T element, int hashValue) {
        int currentPosition = hashValue;
        int i = 0;
        int maxChainLength = (table.length - 1) / 2;
        while (i <= maxChainLength && status[currentPosition] != Status.EMPTY) {
            if (status[currentPosition] == Status.OCCUPIED && table[currentPosition].equals(element)) {
                return currentPosition;
            }

            i++;
            currentPosition = (hashValue + i * i) % table.length;
        }
        return -1;
    }

    @Override
    public boolean insert(T element) {
        int hashValue = hash(element);

        int maxChainLength = (table.length - 1) / 2;
        int i = 0;
        boolean endOfChain = false;
        int firstAvailablePosition = -1;

        while (!endOfChain && i <= maxChainLength) {
            int currentPosition = (hashValue + i * i) % table.length;
            if (status[currentPosition] == Status.OCCUPIED) {
                if (table[currentPosition].equals(element)) {
                    return false;
                }
            } else {
                if (firstAvailablePosition == -1) {
                    firstAvailablePosition = currentPosition;
                }
                if (status[currentPosition] == Status.EMPTY) {
                    endOfChain = true;
                }
            }
            i++;
        }
        if (firstAvailablePosition == -1 || loadFactor() > 0.7) {
            expand();
            hashValue = hash(element);
            add(element, hashValue);
        } else {
            status[firstAvailablePosition] = Status.OCCUPIED;
            table[firstAvailablePosition] = element;
            numElements++;
        }
        return true;
    }

    private void add(T element, int hashValue) {
        boolean foundPosition = false;
        int i = 0;

        while (!foundPosition) {
            int currentPosition = (hashValue + i * i) % table.length;
            if ( status[currentPosition] != Status.OCCUPIED) {
                status[currentPosition] = Status.OCCUPIED;
                table[currentPosition] = element;
                numElements++;
                foundPosition = true;
            } else {
                i++;
            }
        }
    }

    private void expand() {
        int oldSize = table.length;
        int size = 2 * oldSize;
        while (!isPrime(size)){
            size++;
        }

        Object[] oldTable = new Object[oldSize];
        Status[] oldStatus = new Status[oldSize];
        for (int i = 0; i < oldSize; i++) {
            oldTable[i] = table[i];
            oldStatus[i] = status[i];
        }
        reset(size);
        for (int i = 0; i < oldSize; i++) {
            if (oldStatus[i] == Status.OCCUPIED) {
                T element = (T) oldTable[i];
                add(element, hash(element));
            }
        }

        oldTable = null;
        oldStatus = null;
    }

    private double loadFactor() {
        return (double) numElements / (double) table.length;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i*i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    @Override
    public boolean remove(T element) {
        int pos = searchCollisionChain(element, hash(element));
        if (pos < 0)
            return false;
        status[pos] = Status.DELETED;
        numElements--;
        return true;
    }
    @Override
    public boolean member(T element) {
        return searchCollisionChain(element, hash(element)) != -1;
    }
}
