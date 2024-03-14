package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Student
 * 
 * @author David Sweasey
 * @author Dr. King
 */
public class StudentTest {

	/** Stores sOne, a student object used for testing */
	private Student sOne;
	
	/** Stores sOneDiff, a student object used for testing */
	private Student sOneDiff;
	
	/** Stores sOneDiff2, a student object used for testing */
	private Student sOneDiff2;
	
	/** Stores sOneDiff3, a student object used for testing */
	private Student sOneDiff3;
	
	/** Stores sOneNull, a student object used for testing */
	private Student sOneNull;

	/** Stores sOneNull2, a student object used for testing */
	private Student sOneNull2;
	
	/** Stores sTwo, a student object used for testing */
	private Student sTwo;
	
	/** Stores sThree, a student object used for testing */
	private Student sThree;
	
	/** Stores sFour, a student object used for testing */
	private Student sFour;
	
	/** Stores sFive, a student object used for testing */
	private Student sFive;

	/**
	 * Creates student objects to be used for testing
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneDiff = new Student("DiffFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneDiff2 = new Student("OneFirst", "OneLast", 2, 1, 1.0, "oneUnityID");
		sOneDiff3 = new Student("ZFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sOneNull = new Student("OneFirst", null, 1, 1, 1.0, "oneUnityID");
		sOneNull2 = new Student(null, null, 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
	}

	/**
	 * Tests setFirst()
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests setLast()
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests setId()
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests setGpa()
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests setUnityID()
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests setCreditHours()
	 */
	@Test
	public void testSetCreditHours() {
		sOne.setCreditHours(15);
		assertEquals(15, sOne.getCreditHours());
	}
	
	/**
	 * Tests compareTo()
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertEquals(0, sOne.compareTo(sOne));
		assertEquals(0, sTwo.compareTo(sTwo));
		assertTrue(sOne.compareTo(sOneDiff) > 0);
		assertTrue(sOne.compareTo(sOneDiff2) < 0);
		assertTrue(sOne.compareTo(sOneDiff3) < 0);
	}
	
	/**
	 * Tests toString()
	 */
	@Test
	public void testToString() {
		assertEquals(sOne.toString(), "Student [first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]");
		assertEquals(sThree.toString(), "Student [first=ThreeFirst, last=ThreeLast, id=3, creditHours=3, gpa=3.0, unityID=threeUnityID]");
	}
	
	/**
	 * Tests toEquals(). The SuppressWarnings is for the first assertion, which ensures that two objects of differing types cannot be equal
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		assertFalse(sOne.equals("sOne"));
		assertFalse(sOne.equals(sOneDiff));
		assertFalse(sOne.equals(sOneDiff2));
		assertFalse(sOneNull.equals(sOne));
		assertFalse(sOneNull2.equals(sOne));
		assertTrue(sOne.equals(sOne));
		assertFalse(sTwo.equals(sFour));
		assertFalse(sFive.equals(sTwo));
	}
	
	/**
	 * Tests hashCode()
	 */
	@Test
	public void testHashCode() {
		assertEquals(1414505017, sOne.hashCode());
		assertNotEquals(1414505017, sTwo.hashCode());
		assertEquals(-2004437337, sTwo.hashCode());
	}
}
