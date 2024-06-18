package ADT.set;


public class OHashSet<T> implements Set<T> {

    private static final int DEFAULT_INITIAL_CAPACITY = 101;

    private static class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }
    }

    private Node[] table;

    public OHashSet() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public OHashSet(int initialCapacity) {
        if (initialCapacity <= 0)
            throw new IllegalArgumentException("nemoguca valicina");
        table = new Node[initialCapacity];
    }

    private int hash(T o) {
        if (o == null)
            throw new IllegalArgumentException("objekat je null");
        return Math.abs((o.hashCode() % table.length));
    }

    private Node[] searchCollisionChain(T element, int hashValue) {
        Node curent = table[hashValue];
        Node prev = null;

        while (curent != null) {
            if (curent.value.equals(element)) {
                Node[] ret = new Node[2];
                ret[0] = curent;
                ret[1] = prev;
                return ret;
            }
            prev = curent;
            curent = curent.next;
        }
        return null;
    }

    @Override
    public boolean insert(T element) {
        int hashValue = hash(element);
        Node[] n = searchCollisionChain(element, hashValue);
        if (n != null)
            return false;

        Node newElement = new Node(element);
        newElement.next = table[hashValue];
        table[hashValue] = newElement;

        return true;
    }

    @Override
    public boolean remove(T element) {
        int hashValue = hash(element);
        Node[] n = searchCollisionChain(element, hashValue);
        if (n == null)
            return false;
//        ako je prvi element samo ga preskocimo, ili ako je negde u sredini onda koristimo prosli element
        if (n[0] == table[hashValue]) {
            table[hashValue] = table[hashValue].next;
        } else {
            n[1].next = n[0].next;
        }
        return true;
    }

    @Override
    public boolean member(T element) {
        return (searchCollisionChain(element, hash(element)) != null);
    }
}
