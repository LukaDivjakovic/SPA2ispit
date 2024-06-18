package ADT.map;

public class OHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private static class Node {
        Object key;
        Object value;
        Node next;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] table;

    public OHashMap() {
        this(DEFAULT_TABLE_SIZE);
    }

    public OHashMap(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("ne moze");
        }
        table = new Node[size];
    }

    private int hash(K key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        return Math.abs(key.hashCode()) % table.length;
    }

    private Node[] searchCollisionChain(K key, int hashValue) {
        Node current = table[hashValue];
        Node prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                Node[] ret = new Node[2];
                ret[0] = current;
                ret[1] = prev;
                return ret;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean insert(K key, V value) {
        int hashValue = hash(key);

        if (searchCollisionChain(key, hashValue) != null) {
            return false;
        }
        Node newNode = new Node(key, value);
        newNode.next = table[hashValue];
        table[hashValue] = newNode;
        return true;
    }

    @Override
    public boolean delete(K key) {
        int hashValue = hash(key);
        Node[] n = searchCollisionChain(key, hashValue);
        if (n == null) {
            return false;
        }
        if (n[0].equals(table[hashValue])) {
            table[hashValue] = table[hashValue].next;
        } else {
            n[1].next = n[0].next;
        }
        return true;
    }

    @Override
    public V get(K key) {
        int hashValue = hash(key);
        Node[] n = searchCollisionChain(key, hashValue);
        if (n == null) {
            return null;
        }

        if (n[0] != table[hashValue]) {
            n[1].next = n[0].next;
            n[0].next = table[hashValue];
            table[hashValue] = n[0];
        }
        return (V) n[0].value;
    }

    @Override
    public boolean modify(K key, V value) {
        int hashValue = hash(key);
        Node[] n = searchCollisionChain(key, hashValue);
        if (n == null) {
            return false;
        }
        n[0].value = value;
        return true;
    }
}
