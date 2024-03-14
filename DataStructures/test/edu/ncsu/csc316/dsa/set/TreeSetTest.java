package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


/**
 * Test class for TreeSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a balanced search tree data structure 
 *
 * @author Dr. King
 * @author David Sweasey
 */
public class TreeSetTest {

	/** The set used for testing */
    private Set<Integer> set;

    /**
     * Create a new instance of a tree-based set before each test case executes
     */      
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
    }

    /**
     * Test the output of the add behavior
     */     
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(6);
        assertEquals(2, set.size());
        
        set.add(7);
        assertEquals(3, set.size());
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertFalse(set.contains(16));
        set.remove(15);
        set.add(16);
        assertTrue(set.contains(16));
        assertFalse(set.contains(15));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        assertEquals(5, (int)set.remove(5));
        assertEquals(4, set.size());
        
        assertEquals(10, (int)set.remove(10));
        assertEquals(3, set.size());
        
        assertEquals(15, (int)set.remove(15));
        assertEquals(2, set.size());
        
        assertEquals(20, (int)set.remove(20));
        assertEquals(1, set.size());
        
        assertEquals(25, (int)set.remove(25));
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
        
        assertNull(set.remove(20));
    }
    
    /**
     * Test the output of the retainAll behavior
     */ 
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.retainAll(other);
        assertFalse(set.contains(5));
        assertTrue(set.contains(10));
        assertFalse(set.contains(15));
        assertTrue(set.contains(20));
        assertFalse(set.contains(25));
        
        assertEquals(2, set.size());
        assertEquals(3, other.size());
    }

    /**
     * Test the output of the removeAll behavior
     */     
    @Test
    public void testRemoveAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(10);
        other.add(20);
        other.add(30);
        
        set.removeAll(other);
        assertTrue(set.contains(5));
        assertFalse(set.contains(10));
        assertTrue(set.contains(15));
        assertFalse(set.contains(20));
        assertTrue(set.contains(25));
        
        assertEquals(3, set.size());
        assertEquals(3, other.size());
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        Set<Integer> other = new TreeSet<Integer>();
        other.add(30);
        other.add(40);
        other.add(50);
        
        set.addAll(other);
        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25)); 
        assertTrue(set.contains(30));
        assertTrue(set.contains(40));
        assertTrue(set.contains(50));
        
        assertEquals(8, set.size());
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        
        Iterator<Integer> it = set.iterator();
        
        assertEquals(5, (int)it.next());
        try {
        	it.remove();
        	fail("Should throw UnsupportedOperationException");
        } catch(Exception e) {
        	assertEquals(UnsupportedOperationException.class, e.getClass());
        }
        assertTrue(it.hasNext());
        assertEquals(10, (int)it.next());
        assertEquals(15, (int)it.next());
        assertEquals(20, (int)it.next());
        assertEquals(25, (int)it.next());
        assertFalse(it.hasNext());
        
        try {
        	it.next();
        	fail("Should throw NoSuchElementException");
        } catch(Exception e) {
        	assertEquals(NoSuchElementException.class, e.getClass());
        }
    }
}
