package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class BinarySearchTreeMapTest {
	
	/** The BinarySearchTree to use for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(2, "two");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(2, (int)tree.root().getElement().getKey());
        
        tree.put(6, "six");
        assertEquals(2, tree.size());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        
        tree.put(4, "four");
        assertEquals(3, tree.size());
        assertEquals(4, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        
        tree.put(9, "nine");
        assertEquals(4, tree.size());
        assertEquals(9, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(0, "zero");
        assertEquals(5, tree.size());
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(1, "one");
        assertEquals(6, tree.size());
        assertEquals(1, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        
        tree.put(1, "notone");
        assertEquals(6, tree.size());
        assertEquals(1, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals("notone", tree.right(tree.left(tree.root())).getElement().getValue());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));
        assertNull(tree.get(4));
        tree.put(5, "five");
        tree.put(6, "six");
        assertEquals("six", tree.get(6));
        tree.put(6, "notsix");
        assertEquals("notsix", tree.get(6));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(8, "eight");
        tree.put(4, "four");
        tree.put(12, "twelve");
        tree.put(6, "six");
        tree.put(2, "two");
        tree.put(10, "ten");
        tree.put(14, "fourteen");
        assertEquals(7, tree.size());
        
        assertEquals("eight", tree.remove(8));
        assertEquals(6, tree.size());
        assertEquals(10, (int)tree.root().getElement().getKey());
        
        assertEquals("twelve", tree.remove(12));
        assertEquals(5, tree.size());
        assertEquals(14, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("four", tree.remove(4));
        assertEquals(4, tree.size());
        assertEquals(6, (int)tree.left(tree.root()).getElement().getKey());
        
        assertEquals("fourteen", tree.remove(14));
        assertEquals(3, tree.size());
        
        assertEquals("ten", tree.remove(10));
        assertEquals(2, tree.size());
        assertEquals(6, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        
        assertEquals("two", tree.remove(2));
        assertEquals(1, tree.size());
        assertEquals(6, (int)tree.root().getElement().getKey());
        
        assertEquals("six", tree.remove(6));
        assertEquals(0, tree.size());
        assertNull(tree.root().getElement());
    }
    
    /**
     * Tests the entrySet behavior
     */
    @Test
    public void testEntrySet() {
    	Iterable<Entry<Integer, String>> collection = tree.entrySet();
    	Iterator<Entry<Integer, String>> it = collection.iterator();
    	assertFalse(it.hasNext());
    }
    
    /**
     * Tests toString
     */
    @Test
    public void testToString() {
    	assertEquals("BalanceableBinaryTree[\nnull\n]", tree.toString());
    }
}