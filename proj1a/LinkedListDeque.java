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

    public void printDeque() {
        Node node = sentinel.next;
        if (node == null) {
            return;
        }

        while(node != sentinel) {
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
        public T item;
        public Node next;
        public Node prev;

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