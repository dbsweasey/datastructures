package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class ArrayBasedListTest {
	/** List of strings used for testing */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        // Test add to an empty list
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        // Test add to the beginning of the list
        list.add(0, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("one", list.get(1));
        
        // Test add to the end of the list
        list.add(2, "zero");
        assertEquals(3, list.size());
        assertEquals("two", list.get(0));
        assertEquals("one", list.get(1));
        assertEquals("zero", list.get(2));
        
        // Test add in the middle of the list
        list.add(1, "four");
        assertEquals(4, list.size());
        assertEquals("two", list.get(0));
        assertEquals("four", list.get(1));
        assertEquals("one", list.get(2));
        assertEquals("zero", list.get(3));
        
        // Add several elements to test ensureCapacity()
        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	// Test addLast for an empty list
    	list.addLast("five");
    	assertEquals(1, list.size());
    	assertEquals("five", list.get(0));
    	assertFalse(list.isEmpty());
    	
        // Add a few elements
    	list.add(0, "one");
    	list.add(1, "three");
    	list.add(2, "four");
    	list.add(1, "two");
    
    	// Test addLast
    	list.addLast("six");
    	assertEquals(6, list.size());
    	assertEquals("one", list.get(0));
    	assertEquals("two", list.get(1));
    	assertEquals("three", list.get(2));
    	assertEquals("four", list.get(3));
    	assertEquals("five", list.get(4));
    	assertEquals("six", list.get(5));
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        // Ensures that an IndexOutOfBounds is thrown when called on an empty list
    	try {
    		list.last();
    		fail("An IndexOutOfBoundsException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	// Returns the only element
    	list.addFirst("one");
    	assertEquals(list.last(), list.get(0));
    	
    	// Returns the last element of many
    	list.addFirst("two");
    	list.addFirst("three");
    	list.addFirst("four");
    	assertEquals(list.last(), list.get(3));
    	assertEquals(list.last(), "one");
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
        // Test addFirst for an empty list
    	list.addFirst("one");
    	assertEquals(1, list.size());
    	assertEquals("one", list.get(0));
    	assertFalse(list.isEmpty());
    	
    	// Add a few elements
    	list.add(1, "two");
    	list.add(2, "three");
    	list.add(3, "four");
    	
    	// Test addFirst
    	list.addFirst("zero");
    	assertEquals(5, list.size());
    	assertEquals("zero", list.get(0));
    	assertEquals("one", list.get(1));
    	assertEquals("two", list.get(2));
    	assertEquals("three", list.get(3));
    	assertEquals("four", list.get(4));
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        // Ensures that an IndexOutOfBounds is thrown when called on an empty list
    	try {
    		list.first();
    		fail("An IndexOutOfBoundsException should have been thrown");
    	} catch (Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	// Returns the only element
    	list.addLast("one");
    	assertEquals(list.first(), list.get(0));
    	
    	// Returns the first element of many
    	list.addLast("two");
    	list.addLast("three");
    	list.addLast("four");
    	assertEquals(list.first(), list.get(0));
    	assertEquals(list.first(), "one");
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }

        it = list.iterator();
        list.addLast("two");
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        it.remove();
        assertEquals(1, list.size());
        assertEquals("two", list.get(0));
        try {
        	it.remove();
        	fail("An IllegalStateException should have been thrown");
        } catch(Exception e) {
        	assertTrue(e instanceof IllegalStateException);
        }
        
        list.addLast("three");
        list.addLast("four");
        assertEquals("two", it.next());
        assertEquals("three", it.next());
        it.remove();
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("four", list.get(1));
        assertTrue(it.hasNext());
        assertEquals("four", it.next());
        it.remove();
        assertEquals(1, list.size());
        assertEquals("two", list.get(0));
        assertFalse(it.hasNext());
        
        it = list.iterator();
        assertEquals("two", it.next());
        it.remove();
        assertFalse(it.hasNext());
        assertTrue(list.isEmpty());
        
        
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	// Make sure nothing can be removed from an empty list
        try {
        	list.remove(0);
        	fail("Should throw an IndexOutOfBounds exception");
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.add(0, "one");
        list.add(1, "two");
        list.add(2, "three");
        list.add(3, "four");
       
        // Remove from middle of list
        assertEquals(list.remove(2), "three");
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("four", list.get(2));
        // Should not be an element at 3
        assertThrows(IndexOutOfBoundsException.class,
        		() -> list.get(3));
        
        // Remove from front of list
        assertEquals(list.remove(0), "one");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("four", list.get(1));
        // Should not be an element at 2
        assertThrows(IndexOutOfBoundsException.class,
        		() -> list.get(2));
        
        // Remove from end of list
        assertEquals(list.remove(1), "four");
        assertEquals(1, list.size());
        assertEquals("two", list.get(0));
        // Should not be an element at 1
        assertThrows(IndexOutOfBoundsException.class,
        		() -> list.get(1));
        
        // Remove only element in list
        assertEquals(list.remove(0), "two");
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        // Should not be an element at 0
        assertThrows(IndexOutOfBoundsException.class,
        		() -> list.get(0));
        
  
        
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
        try {
        	list.removeFirst();
        	fail("Should throw an IndexOutOfBounds exception");
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.add(0, "one");
        list.add(1, "two");
        list.add(2, "three");
        
        assertEquals(list.removeFirst(), "one");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("three", list.get(1));
        
        assertEquals(list.removeFirst(), "two");
        assertEquals(1, list.size());
        assertEquals("three", list.get(0));
        
        assertEquals(list.removeFirst(), "three");
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
           
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
        try {
        	list.removeLast();
        	fail("Should throw an IndexOutOfBounds exception");
        } catch (Exception e) {
        	assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
        list.add(0, "one");
        list.add(1, "two");
        list.add(2, "three");
        
        assertEquals(list.removeLast(), "three");
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        
        assertEquals(list.removeLast(), "two");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        
        assertEquals(list.removeLast(), "one");
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	try {
    		list.set(0, "one");
    		fail("Should throw an IndexOutOfBounds exception");
    	} catch (Exception e) {
    		assertTrue(e instanceof IndexOutOfBoundsException);
    	}
    	
    	list.addLast("one");
    	assertEquals("one", list.set(0, "two"));
    	assertEquals(1, list.size());
    	assertEquals("two", list.get(0));
    	
    	list.addLast("four");
    	assertEquals("four", list.set(1, "three"));
    	assertEquals(2, list.size());
    	assertEquals("two", list.get(0));
    	assertEquals("three", list.get(1));
    	
    	assertEquals("three", list.set(1, "five"));
    	assertEquals(2, list.size());
    	assertEquals("two", list.get(0));
    	assertEquals("five", list.get(1));
    }
}