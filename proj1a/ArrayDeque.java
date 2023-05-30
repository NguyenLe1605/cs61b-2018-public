public class ArrayDeque<T> {
    private int size = 0;
    private int capacity;
    private T[] items;
    private int first = 0;
    private int last = 0;
    private static final int INITIAL_CAPACITY = 8;
    private static final int MIN_RESIZE_THRESHOLD = 16;
    private static final double USAGE_FACTOR = 0.25;

    public ArrayDeque() {
        capacity = INITIAL_CAPACITY;
        items = (T[]) new Object[capacity];
        first = capacity / 2;
        last = capacity / 2 - 1;
    }
    private int minusOne(int index) {
        int result = (index - 1) % capacity;
        if (result < 0) {
            result = capacity - 1;
        }

        return result;
    }

    private int plusOne(int index) {
        return (index + 1) % capacity;
    }

    private void resize(double factor) {
        int newCapacity = (int) (capacity * factor);
        T[] newItems = (T[]) new Object[newCapacity];
        int numOfFirstSlots = newCapacity / 4;
        for (int i = 0; i < size; i++) {
            int index = (i + first) % capacity;
            newItems[i + numOfFirstSlots] = items[index];
        }

        first = numOfFirstSlots;
        last = first + size - 1;
        capacity = newCapacity;
        items = newItems;
    }

    public void addFirst(T value) {
        if (size == capacity) {
            resize(2);
        }
        size += 1;
        first = minusOne(first);
        items[first] = value;
    }

    public void addLast(T value) {
        if (size == capacity) {
            resize(2);
        }
        size += 1;
        last = plusOne(last);
        items[last] = value;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T value = items[first];
        items[first] = null;
        first = plusOne(first);
        size -= 1;

        if (capacity >= MIN_RESIZE_THRESHOLD && (size * 1.0 / capacity) < USAGE_FACTOR) {
            resize(2 * USAGE_FACTOR);
        }

        return value;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T value = items[last];
        items[last] = null;
        last = minusOne(last);
        size -= 1;
        if (capacity >= MIN_RESIZE_THRESHOLD && (size * 1.0 / capacity) < USAGE_FACTOR) {
            resize(2 * USAGE_FACTOR);
        }
        return value;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int idx = (first + index) % capacity;
        return items[idx];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int index = (i + first) % capacity;
            System.out.print(items[index] + " ");
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
