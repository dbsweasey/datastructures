package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for StudentGPAComparator
 * 
 * @author David Sweasey
 * @author Dr. King
 */
public class StudentGPAComparatorTest {

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
	
	/** Stores sSix, a student object used for testing */
	private Student sSix;
	
	/** Stores sSeven, a student object used for testing */
	private Student sSeven;

	/** Stores the GPA comparator */
	private StudentGPAComparator comparator;

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
		sSix = new Student("FiveFirst", "FiveLast", 6, 5, 5.0, "fiveUnityID2");
		sSeven = new Student("SevenFirst", "FiveLast", 7, 7, 5.0, "sevenUnityID");

		comparator = new StudentGPAComparator();
	}

	/**
	 * Ensures that the comparator accurately compares two students based on GPA
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertTrue(comparator.compare(sOne, sThree) > 0);
		assertTrue(comparator.compare(sThree, sOne) < 0);
		assertTrue(comparator.compare(sThree, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sThree) < 0);
		assertFalse(comparator.compare(sFour, sThree) > 0);
		assertTrue(comparator.compare(sFour, sFive) > 0);
		assertTrue(comparator.compare(sSix, sFive) > 0);
		assertFalse(comparator.compare(sFive, sSix) > 0);
		assertTrue(comparator.compare(sFive, sSeven) < 0);
		assertFalse(comparator.compare(sSeven, sFive) < 0);
	}

}
