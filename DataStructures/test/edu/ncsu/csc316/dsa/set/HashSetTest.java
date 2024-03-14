package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for HashSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a hash table data structure 
 *
 * @author Dr. King
 * @author David Sweasey
 */
public class HashSetTest {
	/** A random set to use for testing */
    private Set<Integer> set;
    
    /** A testing set with constant alpha and beta values for testing */
    private Set<Integer> testSet;
    
    /**
     * Create new instances of a hash-based set before each test case executes
     */ 
    @Before
    public void setUp() {
        // Will use a "production" hash map with random alpha, beta values
        set = new HashSet<Integer>();
        
        // Will use our hash map for testing, which uses alpha=1, beta=1, prime=7
        testSet = new HashSet<Integer>(true);
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
        // Since our hash map uses random numbers, it can
        // be difficult to test that our hash set iterator returns
        // values in a certain order.
        // We could approach this problem with a few different options:
        // (1) instead of checking the specific order of entries
        //      visited by the iterator, we could save each
        //      iterator.next() into an array, then check that the 
        //      array *contains* the entry, regardless of its exact location
        //
        // (2) implement an isTesting flag for HashSet, similar to testSet in the setUp method. 
        //     This is the more appropriate option since we can control the
        //     entire execution and know exactly which values should appear
        //     in the set and in the correct expected order from using the iterator 
        //
        // Revisit your test cases for HashMap iterator -- those tests can be adapted
        //     to work here, too, since you have already worked out the details
        //     of where entries with certain keys will be stored and in what order
        //     they will be stored
        assertTrue(testSet.isEmpty());
        testSet.add(3);
        testSet.add(5);
        testSet.add(2);
        testSet.add(4);
        testSet.add(1);
        testSet.add(6);
        assertEquals(6, testSet.size());
        assertFalse(testSet.isEmpty());
        
        Iterator<Integer> it = testSet.iterator();
        assertTrue(it.hasNext());
        assertEquals(6, (int)it.next()); // should be index 0
        assertEquals(1, (int)it.next()); // should be index 2
        assertEquals(2, (int)it.next()); // should be index 3
        assertEquals(3, (int)it.next()); // should be index 4
        assertEquals(4, (int)it.next()); // should be index 5
        assertEquals(5, (int)it.next()); // should be index 6   
        assertFalse(it.hasNext());
        
        Set<Integer> clear = new HashSet<Integer>();
        testSet.retainAll(clear);
        
        testSet.add(6);
        testSet.add(13);
        testSet.add(19);
        testSet.add(20);
        testSet.add(2);
        testSet.add(5);
        
        it = testSet.iterator();
        assertTrue(it.hasNext());
        try {
        	it.remove();
        	fail("Should be unsupported");
        } catch(Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        assertEquals(6, (int)it.next());
        assertEquals(13, (int)it.next());
        assertEquals(20, (int)it.next());
        assertEquals(2, (int)it.next());
        assertEquals(19, (int)it.next());
        assertEquals(5, (int)it.next());
        
        try {
        	it.next();
        	fail("Should throw a NoSuchElementException");
        } catch(Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }
}