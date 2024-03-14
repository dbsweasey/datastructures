package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure 
 *
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class SeparateChainingHashMapTest {
	
	/** A map used for testing */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        map = new SeparateChainingHashMap<Integer, String>(7, true);
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        assertNull(map.put(7, "string7"));
        assertEquals(3, map.size());
        it = map.entrySet().iterator();
        assertEquals(7, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        
        assertNull(map.put(10, "string10"));
        assertEquals("string10", map.put(10, "string10-2"));
        assertNull(map.put(24, "string24"));
        assertEquals(5, map.size());
        it = map.entrySet().iterator();
        assertEquals(7, (int)it.next().getKey());
        assertEquals(3, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(24, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.get(3));
        map.put(3, "string3");
        assertEquals(map.get(3), "string3");
        map.put(10, "string10");
        assertEquals(map.get(3), "string3");
        assertEquals(map.get(10), "string10");
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.remove(3));
        map.put(3, "string3");
        assertEquals(1, map.size());
        assertEquals("string3", map.remove(3));
        assertEquals(0, map.size());
        map.put(3, "string3");
        map.put(10, "string10");
        assertEquals("string3", map.remove(3));
        assertEquals("string10", map.get(10));
        assertEquals(1, map.size());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */   
    @Test
    public void testIterator() {
        Iterator<Integer> it = map.iterator();
        assertFalse(it.hasNext());
    	
        map.put(6, "string6");
        map.put(13, "string13");
        map.put(19, "string19");
        map.put(20, "string20");
        map.put(2, "string2");
        map.put(5, "string12");
        
        it = map.iterator();
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
        assertEquals(5, (int)it.next());
        assertEquals(19, (int)it.next());
        
        try {
        	it.next();
        	fail("Should throw a NoSuchElementException");
        } catch(Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }
    
    /**
     * Test the output of the entrySet() behavior
     */   
    @Test
    public void testEntrySet() {
        map.put(6, "string6");
        map.put(4, "string4");
        map.put(13, "string13");
        map.put(10, "string10");
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
        assertTrue(it.hasNext());
        try {
        	it.remove();
        	fail("Should be unsupported");
        } catch(Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        assertEquals(6, (int)it.next().getKey());
        assertEquals(13, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        
        try {
        	it.next();
        	fail("Should throw a NoSuchElementException");
        } catch(Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }
    
    /**
     * Test the output of the values() behavior
     */   
    @Test
    public void testValues() {
        map.put(6, "string6");
        map.put(4, "string4");
        map.put(13, "string13");
        map.put(10, "string10");
        
        Iterator<String> it = map.values().iterator();
        assertTrue(it.hasNext());
        try {
        	it.remove();
        	fail("Should be unsupported");
        } catch(Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        assertEquals("string6", it.next());
        assertEquals("string13", it.next());
        assertEquals("string10", it.next());
        assertEquals("string4", it.next());
        
        try {
        	it.next();
        	fail("Should throw a NoSuchElementException");
        } catch(Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }
    
    /**
     * Test constructors of SeparateChainingHashMap
     */
    @Test
    public void testConstructors() {
    	map = new SeparateChainingHashMap<Integer, String>();
    	assertNull(map.put(5, "string5"));
    	assertEquals("string5", map.get(5));
    	assertEquals("string5", map.remove(5));
    	
    	map = new SeparateChainingHashMap<Integer, String>(1);
    	assertNull(map.put(5, "string5"));
    	assertEquals("string5", map.get(5));
    	assertEquals("string5", map.remove(5));
    	
    	map = new SeparateChainingHashMap<Integer, String>(false);
    	assertNull(map.put(5, "string5"));
    	assertEquals("string5", map.get(5));
    	assertEquals("string5", map.remove(5));
    }
}

