package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;


/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King
 * @author David Sweasey
 */
public class HeapPriorityQueueTest {

	/** The heap used for testing PriorityQueue */
    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(10, "10");
        assertEquals(2, heap.size());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(1, "one");
        assertEquals(3, heap.size());
        assertEquals(1, (int)heap.min().getKey());
        
        heap.insert(3, "three");
        assertEquals(4, heap.size());
        assertEquals(1, (int)heap.min().getKey());
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.min());
        
        heap.insert(4, "four");
        assertEquals(4, (int)heap.min().getKey());
        assertEquals("four", heap.min().getValue());
        
        heap.insert(2, "two");
        assertEquals(2, (int)heap.min().getKey());
        assertEquals("two", heap.min().getValue());
        
        heap.deleteMin();
        assertEquals(4, (int)heap.min().getKey());
        assertEquals("four", heap.min().getValue());
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertEquals(8, (int)heap.deleteMin().getKey());
        assertEquals(0, heap.size());
        
        heap.insert(8, "eight");
        heap.insert(4, "four");
        heap.insert(9, "nine");
        heap.insert(10, "ten");
        heap.insert(2, "two");
        assertEquals(5, heap.size());
        assertEquals(2, (int)heap.deleteMin().getKey());
        assertEquals(4, (int)heap.min().getKey());
        assertEquals(4, heap.size());
        
        assertEquals(4, (int)heap.deleteMin().getKey());
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(3, heap.size());
        
        assertEquals(8, (int)heap.deleteMin().getKey());
        assertEquals(9, (int)heap.min().getKey());
        assertEquals(2, heap.size());
        
        assertEquals(9, (int)heap.deleteMin().getKey());
        assertEquals(10, (int)heap.min().getKey());
        assertEquals(1, heap.size());
        
        assertEquals(10, (int)heap.deleteMin().getKey());
        assertNull(heap.min());
        assertEquals(0, heap.size());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        sHeap.insert(s5, "student5");
        assertEquals(1, sHeap.size());
        assertEquals("student5", sHeap.min().getValue());
        
        sHeap.insert(s2, "student2");
        assertEquals(2, sHeap.size());
        assertEquals("student2", sHeap.min().getValue());
        
        sHeap.insert(s3, "student3");
        assertEquals(3, sHeap.size());
        assertEquals("student2", sHeap.min().getValue());
        
        assertEquals("student2", sHeap.deleteMin().getValue());
        assertEquals(2, sHeap.size());
        assertEquals("student3", sHeap.min().getValue());
        
        sHeap.insert(s4, "student4");
        assertEquals(3, sHeap.size());
        assertEquals("student3", sHeap.min().getValue());
        
        sHeap.insert(s1, "student1");
        assertEquals(4, sHeap.size());
        assertEquals("student1", sHeap.min().getValue());
        
        assertEquals("student1", sHeap.deleteMin().getValue());
        assertEquals(3, sHeap.size());
        assertEquals("student3", sHeap.min().getValue());
        
        assertEquals("student3", sHeap.deleteMin().getValue());
        assertEquals(2, sHeap.size());
        assertEquals("student4", sHeap.min().getValue());
        
        assertEquals("student4", sHeap.deleteMin().getValue());
        assertEquals(1, sHeap.size());
        assertEquals("student5", sHeap.min().getValue());
        
        assertEquals("student5", sHeap.deleteMin().getValue());
        assertEquals(0, sHeap.size());
        assertNull(sHeap.min());
    }
}