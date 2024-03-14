package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class ArrayBasedQueueTest {

	/** Queue field used for testing */
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        
        queue.enqueue("three");
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
        queue.enqueue("seven");
        queue.enqueue("eight");
        assertEquals(8, queue.size());
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");      
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
        
        queue.enqueue("one");
        assertEquals("one", queue.dequeue());
        assertEquals(0, queue.size());
        
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        assertEquals("one", queue.dequeue());
        assertEquals(2, queue.size());
        
        queue.enqueue("four");
        queue.enqueue("five");
        assertEquals(4, queue.size());
        assertEquals("two", queue.dequeue());
        assertEquals("three", queue.dequeue());
        assertEquals("four", queue.dequeue());
        assertEquals("five", queue.dequeue());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");      
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }  

    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
        assertEquals(0, queue.size());
        queue.enqueue("one");
        assertEquals("one", queue.front());
        assertEquals(1, queue.size());
        queue.enqueue("two");
        assertEquals("one", queue.front());
        queue.dequeue();
        assertEquals("two", queue.front());
        queue.dequeue();
        try {
            queue.front();
            fail("NoSuchElementException should have been thrown.");      
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }   
        queue.enqueue("one");
        queue.enqueue("two");
        assertEquals("one", queue.front());
        queue.dequeue();
        queue.enqueue("three");
        assertEquals("two", queue.front());
        queue.dequeue();
        assertEquals("three", queue.front());
        queue.dequeue();
        queue.enqueue("four");
        assertEquals("four", queue.front());
        
    }

}
