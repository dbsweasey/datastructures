package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author David Sweasey
 */
public class UpTreeDisjointSetForestTest {

	/** The set to use for testing */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
        Position<String> four = set.makeSet("three");
        assertEquals("three", four.getElement());
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
      	assertEquals(two, set.find("two"));
      	
      	set.union(one, two);
      	assertEquals(two, set.find("one"));
      	assertEquals(two, set.find("two"));
      	
      	set.union(two, three);
      	assertEquals(two, set.find("three"));
      	assertEquals(two, set.find("one"));
      	assertEquals(two, set.find("two"));
      	
      	set.union(five, six);
      	assertEquals(six, set.find("five"));
      	assertEquals(six, set.find("six"));
      	
      	set.union(six, ten);
      	assertEquals(six, set.find("ten"));
      	assertEquals(six, set.find("five"));
      	assertEquals(six, set.find("six"));
      	
      	set.union(six, nine);
      	assertEquals(six, set.find("nine"));
      	assertEquals(six, set.find("ten"));
      	assertEquals(six, set.find("five"));
      	assertEquals(six, set.find("six"));
      	
      	set.union(six, two);
      	assertEquals(six, set.find("nine"));
      	assertEquals(six, set.find("ten"));
      	assertEquals(six, set.find("five"));
      	assertEquals(six, set.find("six"));
      	assertEquals(six, set.find("three"));
      	assertEquals(six, set.find("one"));
      	assertEquals(six, set.find("two"));
      	
      	set.union(seven, six);
      	assertEquals(six, set.find("seven"));
      	
      	set.union(eight, four);
      	set.union(four, six);
      	assertEquals(six, set.find("four"));
      	assertEquals(six, set.find("eight"));
    }
}