package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 * @author David Sweasey
 */
public class HeapAdaptablePriorityQueueTest {

	/** The heap to use for testing adaptaple priority queues */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e0, 9);
        heap.replaceKey(e8, 8);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e1, 0);
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e7, -4);
        assertEquals(-4, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e6, -4);
        assertEquals(-4, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e5, -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("five", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e2, 4);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("five", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e4, -3);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("five", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e3, -8);
        assertEquals(-8, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(9, heap.size());
        
        heap.replaceKey(e3, -2);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("five", heap.min().getValue());
        assertEquals(9, heap.size());
        
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("EIGHT",  e8.getValue());
        
        heap.replaceValue(e7, "SEVEN");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("SEVEN",  e7.getValue());
        
        heap.replaceValue(e6, "SIX");
        heap.replaceKey(e6, -1);
        assertEquals(-1, (int)heap.min().getKey());
        assertEquals("SIX", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("SIX",  e6.getValue());
        
        heap.replaceValue(e5, "FIVE");
        assertEquals(-1, (int)heap.min().getKey());
        assertEquals("SIX", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("FIVE",  e5.getValue());
        
        heap.replaceValue(e5, "FIVE");
        assertEquals(-1, (int)heap.min().getKey());
        assertEquals("SIX", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("FIVE",  e5.getValue());
        
        heap.replaceValue(e4, "FOUR");
        assertEquals(-1, (int)heap.min().getKey());
        assertEquals("SIX", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("FOUR",  e4.getValue());
        
        heap.replaceValue(e3, "THREE");
        assertEquals(-1, (int)heap.min().getKey());
        assertEquals("SIX", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("THREE",  e3.getValue());
        
        heap.replaceValue(e2, "TWO");
        assertEquals(-1, (int)heap.min().getKey());
        assertEquals("SIX", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("TWO",  e2.getValue());
        
        heap.replaceValue(e1, "ONE");
        heap.replaceKey(e0, -10);
        assertEquals(-10, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("ONE",  e1.getValue());
        
        heap.replaceValue(e0, "NEGTEN");
        assertEquals(-10, (int)heap.min().getKey());
        assertEquals("NEGTEN", heap.min().getValue());
        assertEquals(9, heap.size());
        assertEquals("NEGTEN",  e0.getValue());
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e7 = heap.insert(7, "seven");
        Entry<Integer, String> e6 = heap.insert(6, "six");
        Entry<Integer, String> e5 = heap.insert(5, "five");
        Entry<Integer, String> e4 = heap.insert(4, "four");
        Entry<Integer, String> e3 = heap.insert(3, "three");
        Entry<Integer, String> e2 = heap.insert(2, "two");
        Entry<Integer, String> e1 = heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(9, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(8, heap.size());
        
        assertEquals(e1, heap.min());
        assertEquals("one", heap.deleteMin().getValue());
        assertEquals(2, (int)heap.min().getKey());
        assertEquals(7, heap.size());
        
        heap.remove(e6);
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        assertEquals(6, heap.size());
        
        heap.remove(e2);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(5, heap.size());
        
        heap.remove(e4);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(4, heap.size());
        
        heap.remove(e5);
        assertEquals(3, (int)heap.min().getKey());
        assertEquals("three", heap.min().getValue());
        assertEquals(3, heap.size());
        
        heap.remove(e3);
        assertEquals(7, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(2, heap.size());
        
        heap.remove(e8);
        assertEquals(7, (int)heap.min().getKey());
        assertEquals("seven", heap.min().getValue());
        assertEquals(1, heap.size());
        
        heap.remove(e7);
        assertEquals(0, heap.size());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        Entry<Student, String> se1 = sHeap.insert(s1, "student1");
        Entry<Student, String> se3 = sHeap.insert(s3, "student3");
        Entry<Student, String> se5 = sHeap.insert(s5, "student5");
        
        assertEquals(s1, sHeap.min().getKey());
        assertEquals("student1", sHeap.min().getValue());
        
        sHeap.replaceKey(se1, s4);
        assertEquals(s3, sHeap.min().getKey());
        assertEquals("student3", sHeap.min().getValue());
        assertEquals(s4, se1.getKey());
        
        sHeap.replaceValue(se1, "student4");
        assertEquals(s3, sHeap.min().getKey());
        assertEquals("student3", sHeap.min().getValue());
        assertEquals("student4", se1.getValue());
        
        sHeap.remove(se3);
        assertEquals(s4, sHeap.min().getKey());
        assertEquals("student4", se1.getValue());
        
        sHeap.replaceKey(se5, s2);
        assertEquals(s2, sHeap.min().getKey());
        assertEquals("student5", sHeap.min().getValue());
        assertEquals(s2, se5.getKey());
        
        sHeap.remove(se5);
        assertEquals(s4, sHeap.min().getKey());
        assertEquals("student4", se1.getValue());
        assertEquals(1, sHeap.size());
        
    }
}
