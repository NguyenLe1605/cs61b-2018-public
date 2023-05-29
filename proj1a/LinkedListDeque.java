public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node();
    }

    public void addFirst(T item) {
        Node node = new Node(item);
        size += 1;
        if (sentinel.next == null) {
            sentinel.link(node);
            node.link(sentinel);
            return;
        }

        Node temp = sentinel.next;

        sentinel.link(node);
        node.link(temp);
    }

    public void addLast(T item) {
        Node node = new Node(item);
        size += 1;
        if (sentinel.prev == null) {
            sentinel.link(node);
            node.link(sentinel);
            return;
        }

        Node temp = sentinel.prev;
        temp.link(node);
        node.link(sentinel);
    }

    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        size -= 1;
        Node firstNode = sentinel.next;

        if (size == 0) {
            sentinel.next = null;
            sentinel.prev = null;
            return firstNode.item;
        }

        Node newFirstNode = firstNode.next;
        sentinel.link(newFirstNode);

        return firstNode.item;
    }

    public T removeLast() {
        if (size <= 0) {
            return null;
        }

        size -= 1;
        Node lastNode = sentinel.prev;

        if (size == 0) {
            sentinel.next = null;
            sentinel.prev = null;
            return lastNode.item;
        }

        Node newLastNode = lastNode.prev;
        newLastNode.link(sentinel);

        return lastNode.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node node = sentinel.next;
        int i = 0;

        while (node != sentinel && node != null) {
            if (i == index) {
                return node.item;
            }
            node = node.next;
            i++;
        }
        return null;
    }

    private T getRecursiveHelper(Node node, int index) {
        if (node == null || node == sentinel) {
            return null;
        }

        if (index == 0) {
            return node.item;
        }

        return getRecursiveHelper(node.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public void printDeque() {
        Node node = sentinel.next;
        if (node == null) {
            return;
        }

        while (node != sentinel) {
            System.out.print(node.item + " ");
            node = node.next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private class Node {
        T item;
        Node next;
        Node prev;

        public Node() {
            this.next = null;
            this.prev = null;
        }

        public Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }

        public void link(Node rhs) {
            this.next = rhs;
            rhs.prev = this;
        }
    }
}
