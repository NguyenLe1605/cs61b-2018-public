package synthesizer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    @Test
    public void testIterator() {
        var actual = new ArrayRingBuffer<Double>(3);
        actual.enqueue(1.0);
        actual.enqueue(2.0);
        actual.enqueue(3.0);
        var expected = List.of(1.0, 2.0, 3.0);
        int index = 0;
        for (var element: actual) {
            assertEquals(expected.get(index), element);
            index += 1;
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
