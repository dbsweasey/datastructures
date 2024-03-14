package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class UnorderedLinkedMapTest {

	/** The map to test */
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        assertNull(map.put(4, "string4"));
        assertEquals("UnorderedLinkedMap[4, 3]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals("string3", map.put(3, "string5"));
        assertEquals("UnorderedLinkedMap[3, 4]", map.toString());
        assertEquals(2, map.size());
        
        assertNull(map.put(5, "string5"));
        assertEquals("UnorderedLinkedMap[5, 3, 4]", map.toString());
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        assertEquals(5, map.size());
        
        assertEquals("string5", map.get(5));
        assertEquals("UnorderedLinkedMap[5, 1, 4, 2, 3]", map.toString());
        assertEquals(5, map.size());
        
        assertNull(map.get(6));
        assertEquals("UnorderedLinkedMap[5, 1, 4, 2, 3]", map.toString());
        assertEquals(5, map.size());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string3", map.remove(3));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5]", map.toString());
        assertEquals(4, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals("UnorderedLinkedMap[4, 2, 5]", map.toString());
        assertEquals(3, map.size());
        
        assertEquals("string2", map.remove(2));
        assertEquals("UnorderedLinkedMap[4, 5]", map.toString());
        assertEquals(2, map.size());
        
        assertNull(map.remove(2));
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertSame(1, it.next());
        
        try {
        	it.remove();
        	fail("Map iterators should not be able to remove elements");
        } catch (Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        
        assertTrue(it.hasNext());
        assertSame(4, it.next());
        
        assertTrue(it.hasNext());
        assertSame(2, it.next());
        
        assertTrue(it.hasNext());
        assertSame(5, it.next());
        
        assertTrue(it.hasNext());
        assertSame(3, it.next());
        
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("At the end of the map, so there should be no more elemnts");
        } catch (Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    } 
 
    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        assertEquals("string1", it.next().getValue());
        
        try {
        	it.remove();
        	fail("Map iterators should not be able to remove elements");
        } catch (Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        
        assertTrue(it.hasNext());
        assertEquals("string4", it.next().getValue());
        
        assertTrue(it.hasNext());
        assertEquals("string2", it.next().getValue());
        
        assertTrue(it.hasNext());
        assertEquals("string5", it.next().getValue());
        
        assertTrue(it.hasNext());
        assertSame(3, it.next().getKey());
        
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("At the end of the map, so there should be no more elemnts");
        } catch (Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<String> values = map.values();
        Iterator<String> it = values.iterator();
        
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        
        try {
        	it.remove();
        	fail("Map iterators should not be able to remove elements");
        } catch (Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("At the end of the map, so there should be no more elemnts");
        } catch (Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }
}