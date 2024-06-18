package sortiranja.sortiranjaListi;

public class SortableList<T extends Comparable<T>> {
    private class Node {
        T info;
        Node next;
        public Node(T info) {this.info = info;}
    }

    private Node root = null;

    public void add(T info) {
        Node newNode = new Node(info);
        newNode.next = root;
        root = newNode;
    }

    public void insertionSort() {
        if (root == null || root.next == null) {
            return;
        }

        Node lastSorted = root;

        while (lastSorted.next != null) {
            Node firstUnsorted = lastSorted.next;

            if (firstUnsorted.info.compareTo(lastSorted.info) >= 0) {
                lastSorted = firstUnsorted;
            } else {
                if (firstUnsorted.info.compareTo(root.info) < 0) {
                    lastSorted.next = firstUnsorted.next;
                    firstUnsorted.next = root;
                    root = firstUnsorted;
                } else {
                    Node current = root;
                    Node lastLeq = null;
                    while (current.info.compareTo(firstUnsorted.info) <= 0) {
                        lastLeq = current;
                        current = current.next;
                    }

                    lastSorted.next = firstUnsorted.next;
                    firstUnsorted.next = lastLeq.next;
                    lastLeq.next = firstUnsorted;
                }
            }
        }
    }

    public void mergeSort() {
        if (root != null)
            root = mergeSort(root);
    }

    public Node mergeSort(Node start) {
        if (start.next == null)
            return start;

        Node l1 = start;
        Node l1End = l1;

        Node l2 = start.next;
        Node l2End = l2;

        Node current = start.next.next;
        while (current != null) {
            l1End.next = current;
            l1End = current;

            current = current.next;

            if (current != null) {
                l2End.next = current;
                l2End = current;
                current = current.next;
            }
        }
        l1End.next = null;
        l2End.next = null;

        l1 = mergeSort(l1);
        l2 = mergeSort(l2);

        return merge(l1, l2);
    }

    private Node merge(Node l1, Node l2) {
        Node root = null;

        if (l1.info.compareTo(l2.info) < 0) {
            root = l1;
            l1 = l1.next;
        } else {
            root = l2;
            l2 = l2.next;
        }

        Node last = root;
        while (l1 != null && l2 != null) {
            if (l1.info.compareTo(l2.info) < 0) {
                last.next = l1;
                last = l1;
                l1 = l1.next;
            } else {
                last.next = l2;
                last = l2;
                l2 = l2.next;
            }
        }
        last.next = (l1 == null) ? l2 : l1;

        return root;
    }

    public void quickSort() {
        if (root != null)
            root = quickSort(root);
    }

    private Node quickSort(Node start) {
        if (start.next == null)
            return start;

        Node pivot = start;

        Node smaller = null, greater = null;

        Node current = pivot.next;
        while (current != null) {
            Node afterCurrent = current.next;
            if (current.info.compareTo(pivot.info) < 0) {
                current.next = smaller;
                smaller = current;
            } else {
                current.next = greater;
                greater = current;
            }
            current = afterCurrent;
        }

        if (smaller != null) smaller = quickSort(smaller);
        if (greater != null) greater = quickSort(greater);

        pivot.next = greater;
        if (smaller == null) return pivot;
        else {
            Node temp = smaller;
            while (temp.next != null) temp = temp.next;
            temp.next = pivot;
            return smaller;
        }
    }
}
