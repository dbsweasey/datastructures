package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 *
 */
public class PositionalLinkedListTest {

	/** Stores the PositionalList used for testing */
    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> p2 = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first, list.first());
        assertEquals(p2, list.last());
        
        Position<String> p3 = list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals(p3, list.first());
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.last());
        
        Position<String> last = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(last, list.last());
        
        Position<String> p2 = list.addFirst("two");
        assertEquals(p2, list.first());
        assertEquals(2, list.size());
        assertEquals(last, list.last());
        
        Position<String> p3 = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(p3, list.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        
        Position<String> first2 = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(first2, list.first());
        assertEquals(first, list.last());
        
        Position<String> first3 = list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals(first3, list.first());
        assertEquals(first, list.last());
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        Position<String> first2 = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(first, list.first());
        assertEquals(first2, list.last());
        
        Position<String> first3 = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(first, list.first());
        assertEquals(first3, list.last());
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
        Position<String> first = list.addLast("one");
        Position<String> p2 = list.addLast("two");
        Position<String> p3 = list.addLast("three");
        Position<String> last = list.addLast("four");
        
        assertNull(list.before(first));
        
        assertEquals(first, list.before(p2));
        assertEquals(p2, list.before(p3));
        assertEquals(p3, list.before(last));
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
        Position<String> first = list.addLast("one");
        Position<String> p2 = list.addLast("two");
        Position<String> p3 = list.addLast("three");
        Position<String> last = list.addLast("four");
        
        assertNull(list.after(last));
        
        assertEquals(p2, list.after(first));
        assertEquals(p3, list.after(p2));
        assertEquals(last, list.after(p3));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> p1 = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(p1, list.first());
        Position<String> p2 = list.addBefore(p1, "two");
        assertEquals(2, list.size());
        assertEquals(p2, list.first());
        assertEquals(p1, list.last());
        Position<String> p3 = list.addBefore(p1, "three");
        assertEquals(3, list.size());
        assertEquals(p2, list.first());
        assertEquals(p3, list.after(p2));
        assertEquals(p3, list.before(p1));
        assertEquals(p1, list.last());
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        Position<String> p2 = list.addAfter(first, "two");
        assertEquals(2, list.size());
        assertEquals(first, list.first());
        assertEquals(p2, list.last());
        
        Position<String> p3 = list.addAfter(first, "three");
        assertEquals(3, list.size());
        assertEquals(first, list.first());
        assertEquals(p3, list.after(first));
        assertEquals(p3, list.before(p2));
        assertEquals(p2, list.last());
        
        Position<String> p4 = list.addAfter(p2, "four");
        assertEquals(4, list.size());
        assertEquals(first, list.first());
        assertEquals(p4, list.last());
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
        assertEquals(0, list.size());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals("one", list.set(first, "two"));
        assertEquals("two", list.first().getElement());
        assertEquals(1, list.size());
        
        Position<String> p2 = list.addLast("three");
        assertEquals(2, list.size());
        assertEquals("three", list.last().getElement());
        assertEquals("three", list.set(p2, "four"));
        assertEquals(2, list.size());
        assertEquals("four", list.last().getElement());
        
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
        Position<String> first = list.addLast("one");
        Position<String> p2 = list.addLast("two");
        Position<String> p3 = list.addLast("three");
        Position<String> last = list.addLast("four");
        
        assertEquals(4, list.size());
        
        // Remove from middle of list
        assertEquals("two", list.remove(p2));
        assertEquals(3, list.size());
        assertEquals(first, list.first());
        assertEquals(last, list.last());
        
        // Remove from end of list
        assertEquals("four", list.remove(last));
        assertEquals(2, list.size());
        assertEquals(first, list.first());
        assertEquals(p3, list.last());
        
        // Remove from front of list
        assertEquals("one", list.remove(first));
        assertEquals(1, list.size());
        assertEquals(p3, list.first());
        assertEquals(p3, list.last());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Iterator<String> it = list.iterator();
        
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());
        
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        it = list.iterator();
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        it.remove();
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        assertEquals(third, list.last());
        assertEquals("two", it.next());
        assertEquals("three", it.next());
        it.remove();
        assertEquals(1, list.size());
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
        assertFalse(it.hasNext());
        it = list.iterator();
        assertEquals("two", it.next());
        assertFalse(it.hasNext());
        it.remove();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("A NoSuchElementException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof NoSuchElementException);
        }
        
        it.remove();
        try {
        	it.remove();
        	fail("An IllegalStateException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalStateException);
        }
        assertEquals(2, list.size());
        assertEquals(second, list.last());
        
        it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        it.remove();
        assertEquals(1, list.size());
        assertEquals(second, list.first());
        assertTrue(it.hasNext());
        assertEquals(second, it.next());
        it.remove();
        assertEquals(0, list.size());
        
    }

}