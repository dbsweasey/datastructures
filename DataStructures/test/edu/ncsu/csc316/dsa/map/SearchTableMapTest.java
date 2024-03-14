package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SearchTableMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class SearchTableMapTest {

	/** The map of integers to test */
    private Map<Integer, String> map;
    
    /** The map of students to test */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SearchTableMap<Integer, String>();
        studentMap = new SearchTableMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SearchTableMap[3]", map.toString());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        
        assertNull(map.put(4, "string4"));
        assertEquals("SearchTableMap[3, 4]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals("string3", map.put(3, "string5"));
        assertEquals("SearchTableMap[3, 4]", map.toString());
        assertEquals(2, map.size());
        assertEquals("string5", map.get(3));
        
        assertNull(map.put(6, "string5"));
        assertEquals("SearchTableMap[3, 4, 6]", map.toString());
        assertEquals(3, map.size());
        
        assertNull(map.put(2, "string2"));
        assertEquals("SearchTableMap[2, 3, 4, 6]", map.toString());
        assertEquals(4, map.size());
        
        assertNull(map.put(5, "string6"));
        assertEquals("SearchTableMap[2, 3, 4, 5, 6]", map.toString());
        assertEquals(5, map.size());
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
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
        assertEquals("SearchTableMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string1", map.remove(1));
        assertEquals("SearchTableMap[2, 3, 4, 5]", map.toString());
        assertEquals(4, map.size());
        
        assertEquals("string3", map.remove(3));
        assertEquals("SearchTableMap[2, 4, 5]", map.toString());
        assertEquals(3, map.size());
        
        assertEquals("string5", map.remove(5));
        assertEquals("SearchTableMap[2, 4]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals("string2", map.remove(2));
        assertEquals("SearchTableMap[4]", map.toString());
        assertEquals(1, map.size());
        
        assertEquals("string4", map.remove(4));
        assertEquals("SearchTableMap[]", map.toString());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        assertEquals(0, studentMap.size());
        assertTrue(studentMap.isEmpty());
        assertNull(studentMap.put(s2, 2));
        assertEquals("SearchTableMap[Student [first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]]", studentMap.toString());
        assertEquals(1, studentMap.size());
        assertFalse(studentMap.isEmpty());
        
        assertNull(studentMap.put(s1, 1));
        assertEquals("SearchTableMap[Student [first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk], "
        		+ "Student [first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]]", studentMap.toString());
        assertEquals(2, studentMap.size());
        
        assertNull(studentMap.put(s4, 4));
        assertEquals("SearchTableMap[Student [first=J, last=J, id=4, creditHours=0, gpa=0.0, unityID=jj], "
        		+ "Student [first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk], "
        		+ "Student [first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]]", studentMap.toString());
        assertEquals(3, studentMap.size());
        
        assertNull(studentMap.put(s3, 3));
        assertEquals("SearchTableMap[Student [first=S, last=H, id=3, creditHours=0, gpa=0.0, unityID=sh], "
        		+ "Student [first=J, last=J, id=4, creditHours=0, gpa=0.0, unityID=jj], "
        		+ "Student [first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk], "
        		+ "Student [first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]]", studentMap.toString());
        assertEquals(4, studentMap.size());
        
        assertSame(2, studentMap.put(s2, 5));
        assertEquals("SearchTableMap[Student [first=S, last=H, id=3, creditHours=0, gpa=0.0, unityID=sh], "
        		+ "Student [first=J, last=J, id=4, creditHours=0, gpa=0.0, unityID=jj], "
        		+ "Student [first=J, last=K, id=1, creditHours=0, gpa=0.0, unityID=jk], "
        		+ "Student [first=J, last=S, id=2, creditHours=0, gpa=0.0, unityID=js]]", studentMap.toString());
        assertEquals(4, studentMap.size());
       
        
        assertSame(1, studentMap.get(s1));
        assertSame(5, studentMap.get(s2));
        assertSame(3, studentMap.get(s3));
        assertSame(4, studentMap.get(s4));
        assertNull(studentMap.get(s5));
        
        assertSame(3, studentMap.remove(s3));
        assertEquals(3, studentMap.size());
        
        assertSame(5, studentMap.remove(s2));
        assertEquals(2, studentMap.size());
        
        assertSame(1, studentMap.remove(s1));
        assertEquals(1, studentMap.size());
        
        assertSame(4, studentMap.remove(s4));
        assertEquals(0, studentMap.size());
        
        assertNull(studentMap.remove(s5));
        assertEquals(0, studentMap.size());
        assertTrue(studentMap.isEmpty());
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
        assertSame(2, it.next());
        
        assertTrue(it.hasNext());
        assertSame(3, it.next());
        
        assertTrue(it.hasNext());
        assertSame(4, it.next());
        
        assertTrue(it.hasNext());
        assertSame(5, it.next());
        
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
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        
        try {
        	it.remove();
        	fail("Map iterators should not be able to remove elements");
        } catch (Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        
        assertTrue(it.hasNext());
        assertEquals("string2", it.next().getValue());
        
        assertTrue(it.hasNext());
        assertEquals("string3", it.next().getValue());
        
        assertTrue(it.hasNext());
        assertSame(4, it.next().getKey());
        
        assertTrue(it.hasNext());
        assertEquals("string5", it.next().getValue());
        
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
        
        Iterator<String> it = map.values().iterator();
        
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        
        try {
        	it.remove();
        	fail("Map iterators should not be able to remove elements");
        } catch (Exception e) {
        	assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail("At the end of the map, so there should be no more elemnts");
        } catch (Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }
}
