package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for CountingSorter
 * 
 * @author David Sweasey
 * @author Dr. King
 */
public class CountingSorterTest {
	
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
	
	/** Stores the CountingSorter used to sort data */
	private CountingSorter<Student> sorter;

	/**
	 * Creates the student objects and sorter
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new CountingSorter<Student>();
	}
	
	/**
	 * Tests CountingSorter on an array of students in a random order
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/**
	 * Tests CountingSorter on an array of students in ascending order
	 */
	@Test
	public void testSortStudentAscending() {
		Student[] ascending = { sOne, sTwo, sThree, sFour, sFive };
		sorter.sort(ascending);
		assertEquals(sOne, ascending[0]);
		assertEquals(sTwo, ascending[1]);
		assertEquals(sThree, ascending[2]);
		assertEquals(sFour, ascending[3]);
		assertEquals(sFive, ascending[4]);
	}
	
	/**
	 * Tests CountingSorter on an array of students in descending order
	 */
	@Test
	public void testSortStudentDescending() {
		Student[] descending = { sFive, sFour, sThree, sTwo, sOne };
		sorter.sort(descending);
		assertEquals(sOne, descending[0]);
		assertEquals(sTwo, descending[1]);
		assertEquals(sThree, descending[2]);
		assertEquals(sFour, descending[3]);
		assertEquals(sFive, descending[4]);
	}

}
