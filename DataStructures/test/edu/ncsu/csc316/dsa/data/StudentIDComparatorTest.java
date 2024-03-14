package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for StudentIDComparator
 * 
 * @author David Sweasey
 * @author Dr. King
 */
public class StudentIDComparatorTest {

	/** Stores sOne, a student object used for testing */
	private Student sOne;
	
	/** Stores sTwo, a student object used for testing */
	private Student sTwo;
	
	/** Stores sThree, a student object used for testing */
	private Student sThree;
	
	/** Stores sFour, a student object used for testing */
	private Student sFour;
	
	/** Stores sFive, a student object used for testing */
	private Student sFive;

	/** Stores the GPA comparator */
	private StudentIDComparator comparator;

	/**
	 * Creates student objects and a new GPA comparator
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Ensures that the comparator accurately compares two students based on student ID
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertTrue(comparator.compare(sOne, sThree) < 0);
		assertTrue(comparator.compare(sThree, sOne) > 0);
		assertTrue(comparator.compare(sThree, sTwo) > 0);
		assertFalse(comparator.compare(sTwo, sThree) > 0);
		assertFalse(comparator.compare(sFour, sThree) < 0);
		assertTrue(comparator.compare(sFour, sFive) < 0);
		
	}


}
