package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class LinkedBinaryTreeTest {

	/** The binary tree of strings used for testing */
    private LinkedBinaryTree<String> tree;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> one;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> two;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> three;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> four;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> five;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> six;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> seven;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> eight;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> nine;
    
    /** LinkedBinaryNode to be put in the tree */
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     * 
     * @param <E> the type to use
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        // Make sure you cannot set a position not in the tree
        Position<String> eleven = new InvalidPosition<String>();
        try {
        	tree.set(eleven, "eleven");
        	fail("Position does not exist within tree so should not be set");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        assertEquals("four", tree.set(four, "four2"));
        assertEquals("four2", tree.left(three).getElement());
        assertEquals(eight, tree.left(four));
        assertEquals(nine, tree.right(four));
        
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(1, tree.numChildren(three));
        assertEquals(0, tree.numChildren(six));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertEquals(one, tree.parent(two));
        assertEquals(one, tree.parent(three));
        assertEquals(four, tree.parent(nine));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertEquals(ten, tree.sibling(six));
        assertNull(tree.sibling(four));
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(four));
        assertTrue(tree.isInternal(three));
        assertFalse(tree.isInternal(six));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(three));
        assertTrue(tree.isLeaf(six));
        assertTrue(tree.isLeaf(nine));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(three, it.next());
        assertEquals(four, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        assertEquals(six, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(ten, it.next());
        assertEquals(two, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertEquals(four, it.next());
        assertEquals(three, it.next());
        assertEquals(one, it.next());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertEquals(six, it.next());
        assertEquals(two, it.next());
        assertEquals(seven, it.next());
        assertEquals(ten, it.next());
        assertEquals(five, it.next());
        assertEquals(one, it.next());
        assertEquals(eight, it.next());
        assertEquals(four, it.next());
        assertEquals(nine, it.next());
        assertEquals(three, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        assertNull(tree.root());
        Iterator<Position<String>> it = tree.preOrder().iterator();
        assertFalse(it.hasNext());
        it = tree.postOrder().iterator();
        assertFalse(it.hasNext());
        it = tree.inOrder().iterator();
        assertFalse(it.hasNext());
        it = tree.levelOrder().iterator();
        assertFalse(it.hasNext());
        
        Position<String> nonExistant = new InvalidPosition<String>();
        
        try {
        	tree.set(nonExistant, "nothing");
        	fail("Cannot set an empty tree");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        try {
        	tree.remove(nonExistant);
        	fail("Cannot remove from an empty tree");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        try {
        	tree.numChildren(nonExistant);
        	fail("No nodes in empty tree");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        try {
        	tree.parent(nonExistant);
        	fail("No nodes in empty tree");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        try {
        	tree.sibling(nonExistant);
        	fail("No nodes in empty tree");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        try {
        	tree.isLeaf(nonExistant);
        	fail("No nodes in empty tree");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        try {
        	tree.isInternal(nonExistant);
        	fail("No nodes in empty tree");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        try {
        	tree.children(nonExistant);
        	fail("No nodes in empty tree");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        assertFalse(tree.isRoot(nonExistant));
        
        one = tree.addRoot("one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        
        // Tests the setNode method
        two = tree.addLeft(one, "two");
        assertEquals(two, tree.setRoot(two));
    }
    
    /**
     * Test the output of the levelOrder traversal behavior
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(three, it.next());
        assertEquals(six, it.next());
        assertEquals(ten, it.next());
        assertEquals(four, it.next());
        assertEquals(seven, it.next());
        assertEquals(five, it.next());
        assertEquals(eight, it.next());
        assertEquals(nine, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        assertEquals(2, tree.size());
        three = tree.addLeft(two, "three");
        assertEquals(3, tree.size());
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertEquals(three, it.next());
        assertEquals(two, it.next());
        assertEquals(one, it.next());
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	one = tree.addRoot("one");
        two = tree.addRight(one, "two");
        assertEquals(2, tree.size());
        three = tree.addRight(two, "three");
        assertEquals(3, tree.size());
        Iterator<Position<String>> it = tree.inOrder().iterator();
        assertEquals(one, it.next());
        assertEquals(two, it.next());
        assertEquals(three, it.next());
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        assertEquals("seven", tree.remove(seven));
        assertEquals(9, tree.size());
        assertNull(tree.sibling(five));
        assertEquals("ten", tree.remove(ten));
        assertEquals(five, tree.right(two));
        try {
        	tree.remove(null);
        	fail("Cannot remove null element");
        } catch (Exception e) {
        	assertEquals(e.getClass(), IllegalArgumentException.class);
        }
        
        Iterator<Position<String>> it = tree.inOrder().iterator();
        try {
        	it.next();
        	it.remove();
        	fail("Cannot remove using an iterator");
        } catch (Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
    }
    
    /**
     * Tests the toString method
     */
    @Test
    public void testTooString() {
    	assertEquals("LinkedBinaryTree[\n]", tree.toString());
    	createTree();
    	assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   five\n three\n  four\n   eight\n   nine\n]", tree.toString());
    }
}