package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        BoundedQueue<Integer> arb = new ArrayRingBuffer<>(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        assertEquals((Integer) 5, arb.dequeue());
        arb.enqueue(9);
        assertEquals((Integer) 6, arb.peek());
        assertTrue(arb.isFull());
        assertFalse(arb.isEmpty());
        for (int i = 0; i < 4; i++) {
            assertFalse(arb.isEmpty());
            arb.dequeue();
            assertFalse(arb.isFull());
        }
        assertTrue(arb.isEmpty());
    }

    @Test
    public void someTest2() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        BoundedQueue<String> arb = new ArrayRingBuffer<>(4);
        arb.enqueue("Hi");
        arb.enqueue("");
        arb.enqueue("B");
        arb.enqueue("C");
        assertTrue(arb.dequeue().equals("Hi"));
        arb.enqueue("");
        assertTrue(arb.peek().equals(""));
        assertTrue(arb.isFull());
        assertFalse(arb.isEmpty());
        for (int i = 0; i < 4; i++) {
            assertFalse(arb.isEmpty());
            arb.dequeue();
            assertFalse(arb.isFull());
        }
        assertTrue(arb.isEmpty());
    }

    @Test
    public void ARBIterTest(){
        BoundedQueue<String> arb = new ArrayRingBuffer<>(4);
        arb.enqueue("Hi");
        arb.enqueue(" 1 ");
        arb.enqueue("B");
        arb.enqueue("C");
        for (String s : arb) {
            System.out.println(s);
        }
//        arb.enqueue("Empty");     // if iterator is destructive, this line will pass.
    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
