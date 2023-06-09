package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity(); // return size of the buffer
    int fillCount(); // return the number of items currently in the buffer
    void enqueue(T x); // add item x to the end
    T dequeue(); // delete and return element at the front
    T peek(); // return (but do not element) from the front

    Iterator<T> iterator();

//     is the buffer empty
    default boolean isEmpty() {
        return fillCount() == 0;
    }

//    is the buffer full
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
