package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 *
 */
public class SplayTreeMapTest {

	/** SplayTree used for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(6, "six");
        assertEquals(1, tree.size());
        assertEquals(6, (int)tree.root().getElement().getKey());
        
        // Zig
        tree.put(11, "eleven");
        assertEquals(2, tree.size());
        assertEquals(11, (int)tree.root().getElement().getKey());
        assertEquals(6, (int)tree.left(tree.root()).getElement().getKey());
        
        // Zig Zig
        tree.put(3, "three");
        assertEquals(3, tree.size());
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(11, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        // Zig Zag (left)
        tree.put(5, "five");
        assertEquals(4, tree.size());
        assertEquals(5, (int)tree.root().getElement().getKey());
        assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(11, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        // Zig Zag (right)
        tree.put(4, "four");
        assertEquals(5, tree.size());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(11, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());  
        
        tree.put(6, "notsix");
        assertEquals(5, tree.size());
        assertEquals(6, (int)tree.root().getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
        tree.put(6, "six");
    	tree.put(11, "eleven");
    	tree.put(3, "three");
    	tree.put(5, "five");
    	tree.put(4, "four");
    	tree.put(8, "eight");
    	tree.put(2, "two");
    	tree.put(7, "seven");
    	assertEquals(8, tree.size());
    	
    	assertEquals("seven", tree.get(7));
    	assertEquals(7, (int)tree.root().getElement().getKey());
    	
    	// Zig Zag (right)
    	assertEquals("six", tree.get(6));
    	assertEquals(6, (int)tree.root().getElement().getKey());
    	assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
    	
    	// Zig Zig -> Zig (left)
    	assertEquals("eleven", tree.get(11));
    	assertEquals(11, (int)tree.root().getElement().getKey());
    	assertEquals(6, (int)tree.left(tree.root()).getElement().getKey());
    	assertNull(tree.right(tree.root()).getElement());
    	
    	// Zig Zig -> Zig (right)
    	assertEquals("four", tree.get(4));
    	assertEquals(4, (int)tree.root().getElement().getKey());
    	assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(11, (int)tree.right(tree.root()).getElement().getKey());
    	
    	// Zig
    	assertEquals("three", tree.get(3));
    	assertEquals(3, (int)tree.root().getElement().getKey());
    	assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());
    	
    	assertEquals("seven", tree.get(7));
    	assertEquals(7, (int)tree.root().getElement().getKey());
    	assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(11, (int)tree.right(tree.root()).getElement().getKey());
    	assertEquals(3, (int)tree.left(tree.left(tree.root())).getElement().getKey());
    	assertEquals(5, (int)tree.right(tree.left(tree.root())).getElement().getKey());
    	assertEquals(8, (int)tree.left(tree.right(tree.root())).getElement().getKey());
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	tree.put(6, "six");
    	tree.put(11, "eleven");
    	tree.put(3, "three");
    	tree.put(5, "five");
    	tree.put(4, "four");
    	tree.put(8, "eight");
    	tree.put(7, "seven");
    	tree.put(2, "two");
    	assertEquals(8, tree.size());
    	
    	// Zig Zag (left)
    	assertEquals("six", tree.remove(6));
    	assertEquals(7, tree.size());
    	assertEquals(5, (int)tree.root().getElement().getKey());
    	assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
    	assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
    	
    	// Zig
    	assertEquals("three", tree.remove(3));
    	assertEquals(6, tree.size());
    	assertEquals(2, (int)tree.root().getElement().getKey());
    	assertEquals(5, (int)tree.right(tree.root()).getElement().getKey());
    	
    	// Remove root
    	assertEquals("two", tree.remove(2));
    	assertEquals(5, tree.size());
    	assertEquals(5, (int)tree.root().getElement().getKey());
    	assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
    	assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
    	
    	// Zig Zig
    	assertEquals("eleven", tree.remove(11));
    	assertEquals(4, tree.size());
    	assertEquals(8, (int)tree.root().getElement().getKey());
    	assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
    	assertNull(tree.right(tree.root()).getElement());
    	
    	// Zig Zig
    	assertEquals("four", tree.remove(4));
    	assertEquals(3, tree.size());
    	assertEquals(5, (int)tree.root().getElement().getKey());
    	assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
    	assertNull(tree.left(tree.root()).getElement());         
    	
    	assertNull(tree.remove(4));
    }
    
    /**
     * Test the other constructor for SplayTree
     */
    @Test
    public void testSplayTree() {
    	tree = new SplayTreeMap<Integer, String>(null);
    	tree.put(3, "three");
    	assertEquals("three", tree.get(3));
    }
}