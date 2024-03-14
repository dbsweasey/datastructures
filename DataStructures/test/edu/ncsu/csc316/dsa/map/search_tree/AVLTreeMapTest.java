package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 *
 */
public class AVLTreeMapTest {

	/** The AVL tree used for testing */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(9, "nine");
        assertEquals(1, tree.size());
        assertEquals(9, (int)tree.root().getElement().getKey());
        
        tree.put(6, "six");
        assertEquals(2, tree.size());
        assertEquals(6, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(3, "three");
        assertEquals(3, tree.size());
        assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.root().getElement().getKey());
        
        tree.put(12, "twelve");
        assertEquals(4, tree.size());
        assertEquals(12, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        
        tree.put(10, "ten");
        assertEquals(5, tree.size());
        assertEquals(6, (int)tree.root().getElement().getKey());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        
        tree.put(11, "eleven");
        assertEquals(6, tree.size());
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(6, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(5, "five");
        assertEquals(7, tree.size());
        assertEquals(5, (int)tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
        
        tree.put(4, "four");
        assertEquals(8, tree.size());
        assertEquals(4, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        
        tree.put(2, "two");
        assertEquals(9, tree.size());
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(12, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(2, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        
        tree.put(1, "one");
        assertEquals(10, tree.size());
        assertEquals(1, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(5, (int)tree.left(tree.right(tree.left(tree.root()))).getElement().getKey());
        
        tree.put(0, "zero");
        assertEquals(11, tree.size());
        assertEquals(0, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(4, (int)tree.root().getElement().getKey());
        assertEquals(6, (int)tree.left(tree.right(tree.root())).getElement().getKey());
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
        tree.put(9, "nine");
        tree.put(6, "six");       
        tree.put(3, "three");       
        tree.put(12, "twelve");        
        tree.put(10, "ten");       
        tree.put(11, "eleven");
        tree.put(5, "five");
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(0, "zero");
        
        assertEquals(11, tree.size());
        assertEquals("three", tree.remove(3));
        assertEquals(10, tree.size());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.root().getElement().getKey());
        
        assertEquals("one", tree.remove(1));
        assertEquals(9, tree.size());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        
        assertEquals("two", tree.remove(2));
        assertEquals(8, tree.size());
        assertEquals(10, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        
        assertEquals("ten", tree.remove(10));
        assertEquals(7, tree.size());
        assertEquals(6, (int)tree.root().getElement().getKey());
        assertEquals(11, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("twelve", tree.remove(12));
        assertEquals(6, tree.size());
        assertEquals(6, (int)tree.root().getElement().getKey());
        assertEquals(4, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(11, (int)tree.right(tree.root()).getElement().getKey());
        
        assertEquals("zero", tree.remove(0));
        assertEquals("four", tree.remove(4));
        assertEquals("five", tree.remove(5));
        assertEquals(3, tree.size());
        assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals(6, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(11, (int)tree.right(tree.root()).getElement().getKey());
       
    }
    
    /**
     * Test the other constructor for AVLTree
     */
    @Test
    public void testAVLTree() {
    	tree = new AVLTreeMap<Integer, String>(null);
    	tree.put(3, "three");
    	assertEquals("three", tree.get(3));
    }
}