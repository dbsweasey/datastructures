package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class RedBlackTreeMapTest {

	/** The RedBlackTree used for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(14, "fourteen");
        assertEquals(1, tree.size());
        assertEquals(14, (int)tree.root().getElement().getKey());
        
        tree.put(10, "ten");
        assertEquals(2, tree.size());
        assertEquals(10, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(7, "nine");
        assertEquals(3, tree.size());
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(14, (int)tree.right(tree.root()).getElement().getKey());
        
        // Case 2
        tree.put(8, "eight");
        assertEquals(4, tree.size());
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(8, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        
        // Case 1
        tree.put(9, "nine");
        assertEquals(5, tree.size());
        assertEquals(8, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(9, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        
        tree.put(4, "four");
        tree.put(17, "seventeen");
        tree.put(15, "fifteen");
        assertEquals(8, tree.size());
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(15, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(17, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(18, "eighteen");
        tree.put(3, "three");
        assertEquals(10, tree.size());
        assertEquals(3, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(4, (int)tree.left(tree.left(tree.root())).getElement().getKey());   
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(tree.isEmpty());
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
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        assertNull(tree.remove(3));
        
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(4, "four");
        tree.put(5, "five");
        tree.put(6, "six");
        tree.put(7, "seven");
        tree.put(8, "eight");
        tree.put(9, "nine");
        tree.put(10, "ten");
        tree.put(11, "eleven");
        tree.put(12, "twelve");
        tree.put(13, "thirteen");
        tree.put(14, "fourteen");
        tree.put(15, "fifteen");
        tree.put(16, "sixteen");
        tree.put(17, "seventeen");
        tree.put(19, "nineteen");
        assertEquals(18, tree.size());
                
        // Case 2
        assertEquals("seven", tree.remove(7));
        assertEquals(17, tree.size());
        assertEquals(8, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        
        assertEquals("five", tree.remove(5));
        assertEquals(16, tree.size());
        
        // Case 3
        assertEquals("six", tree.remove(6));
        assertEquals(15, tree.size());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.right(tree.left(tree.root()))).getElement().getKey());
        
        assertEquals("thirteen", tree.remove(13));
        assertEquals(14, tree.size());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(16, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(17, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        assertEquals(14, (int)tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
        
        assertEquals("nineteen", tree.remove(19));
        assertEquals(13, tree.size());
        assertEquals(8, (int)tree.root().getElement().getKey());
        
        assertEquals("one", tree.remove(1));
        assertEquals("two", tree.remove(2));
        assertEquals(12, (int)tree.root().getElement().getKey());
        
        assertEquals("four", tree.remove(4));
        assertEquals("three", tree.remove(3));
        assertEquals(10, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(9, tree.size());
    }
    
    /**
     * Test the other constructor for RedBlackTree
     */
    @Test
    public void testRedBlackTree() {
    	tree = new RedBlackTreeMap<Integer, String>(null);
    	tree.put(3, "three");
    	assertEquals("three", tree.get(3));
    }
}