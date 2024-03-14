package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for QuickSorter
 * 
 * @author David Sweasey
 */
public class QuickSorterTest {

	/** Integer data used for testing in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	
	/** Integer data used for testing in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	
	/** Integer data used for testing in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };

	/** Sorter object used to sort integers */
	private Sorter<Integer> integerSorter;
	
	/** Sorter object used to sort students */
	private Sorter<Student> studentSorter;

	/**
	 * Creates the sorters used for testing
	 */
	@Before
	public void setUp() {
		integerSorter = new QuickSorter<Integer>();
		studentSorter = new QuickSorter<Student>();
	}

	/**
	 * Tests QuicjSorter with varying lists of integers
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(0, Integer.compare(1, dataAscending[0]));
		assertEquals(0, Integer.compare(2, dataAscending[1]));
		assertEquals(0, Integer.compare(3, dataAscending[2]));
		assertEquals(0, Integer.compare(4, dataAscending[3]));
		assertEquals(0, Integer.compare(5, dataAscending[4]));

		integerSorter.sort(dataDescending);
		assertEquals(0, Integer.compare(1, dataDescending[0]));
		assertEquals(0, Integer.compare(2, dataDescending[1]));
		assertEquals(0, Integer.compare(3, dataDescending[2]));
		assertEquals(0, Integer.compare(4, dataDescending[3]));
		assertEquals(0, Integer.compare(5, dataDescending[4]));

		integerSorter.sort(dataRandom);
		assertEquals(0, Integer.compare(1, dataRandom[0]));
		assertEquals(0, Integer.compare(2, dataRandom[1]));
		assertEquals(0, Integer.compare(3, dataRandom[2]));
		assertEquals(0, Integer.compare(4, dataRandom[3]));
		assertEquals(0, Integer.compare(5, dataRandom[4]));
	}

	/**
	 * Tests QuickSorter with varying lists of students
	 */
	@Test
	public void testSortStudent() {
		Student s1 = new Student("Jason", "Alastair", 4410, 13, 3.45, "jalastair");
		Student s2 = new Student("Alfred", "Smith", 4404, 15, 3.82, "asmith");
		Student s3 = new Student("Colby", "Smith", 4392, 14, 2.99, "csmith");
		Student s4 = new Student("Colby", "Smith", 4399, 16, 4.00, "csmith2");
		Student s5 = new Student("David", "Sweasey", 4400, 13, 4.00, "dbswease");
		
		Student[] studentAscending = { s1, s2, s3, s4, s5 };
		Student[] studentDescending = { s5, s4, s3, s2, s1 };
		Student[] studentRandom = {s3, s5, s2, s1, s4};
		
		studentSorter.sort(studentAscending);
		assertEquals(0, s1.compareTo(studentAscending[0]));
		assertEquals(0, s2.compareTo(studentAscending[1]));
		assertEquals(0, s3.compareTo(studentAscending[2]));
		assertEquals(0, s4.compareTo(studentAscending[3]));
		assertEquals(0, s5.compareTo(studentAscending[4]));
		
		studentSorter.sort(studentDescending);
		assertEquals(0, s1.compareTo(studentDescending[0]));
		assertEquals(0, s2.compareTo(studentDescending[1]));
		assertEquals(0, s3.compareTo(studentDescending[2]));
		assertEquals(0, s4.compareTo(studentDescending[3]));
		assertEquals(0, s5.compareTo(studentDescending[4]));
		
		studentSorter.sort(studentRandom);
		assertEquals(0, s1.compareTo(studentRandom[0]));
		assertEquals(0, s2.compareTo(studentRandom[1]));
		assertEquals(0, s3.compareTo(studentRandom[2]));
		assertEquals(0, s4.compareTo(studentRandom[3]));
		assertEquals(0, s5.compareTo(studentRandom[4]));
		
	}
	
	/**
	 * Tests QuickSorter using FirstELementSelector
	 */
	@Test
	public void testSortFirstElementSelector() {
		studentSorter = new QuickSorter<Student>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		
		Student s1 = new Student("Jason", "Alastair", 4410, 13, 3.45, "jalastair");
		Student s2 = new Student("Alfred", "Smith", 4404, 15, 3.82, "asmith");
		Student s3 = new Student("Colby", "Smith", 4392, 14, 2.99, "csmith");
		Student s4 = new Student("Colby", "Smith", 4399, 16, 4.00, "csmith2");
		Student s5 = new Student("David", "Sweasey", 4400, 13, 4.00, "dbswease");
		
		Student[] studentAscending = { s1, s2, s3, s4, s5 };
		Student[] studentDescending = { s5, s4, s3, s2, s1 };
		Student[] studentRandom = {s3, s5, s2, s1, s4};
		
		studentSorter.sort(studentAscending);
		assertEquals(0, s1.compareTo(studentAscending[0]));
		assertEquals(0, s2.compareTo(studentAscending[1]));
		assertEquals(0, s3.compareTo(studentAscending[2]));
		assertEquals(0, s4.compareTo(studentAscending[3]));
		assertEquals(0, s5.compareTo(studentAscending[4]));
		
		studentSorter.sort(studentDescending);
		assertEquals(0, s1.compareTo(studentDescending[0]));
		assertEquals(0, s2.compareTo(studentDescending[1]));
		assertEquals(0, s3.compareTo(studentDescending[2]));
		assertEquals(0, s4.compareTo(studentDescending[3]));
		assertEquals(0, s5.compareTo(studentDescending[4]));
		
		studentSorter.sort(studentRandom);
		assertEquals(0, s1.compareTo(studentRandom[0]));
		assertEquals(0, s2.compareTo(studentRandom[1]));
		assertEquals(0, s3.compareTo(studentRandom[2]));
		assertEquals(0, s4.compareTo(studentRandom[3]));
		assertEquals(0, s5.compareTo(studentRandom[4]));
	}
	
	/**
	 * Tests QuickSorter using LastELementSelector
	 */
	@Test
	public void testSortLastElementSelector() {
		studentSorter = new QuickSorter<Student>(QuickSorter.LAST_ELEMENT_SELECTOR);
		
		Student s1 = new Student("Jason", "Alastair", 4410, 13, 3.45, "jalastair");
		Student s2 = new Student("Alfred", "Smith", 4404, 15, 3.82, "asmith");
		Student s3 = new Student("Colby", "Smith", 4392, 14, 2.99, "csmith");
		Student s4 = new Student("Colby", "Smith", 4399, 16, 4.00, "csmith2");
		Student s5 = new Student("David", "Sweasey", 4400, 13, 4.00, "dbswease");
		
		Student[] studentAscending = { s1, s2, s3, s4, s5 };
		Student[] studentDescending = { s5, s4, s3, s2, s1 };
		Student[] studentRandom = {s3, s5, s2, s1, s4};
		
		studentSorter.sort(studentAscending);
		assertEquals(0, s1.compareTo(studentAscending[0]));
		assertEquals(0, s2.compareTo(studentAscending[1]));
		assertEquals(0, s3.compareTo(studentAscending[2]));
		assertEquals(0, s4.compareTo(studentAscending[3]));
		assertEquals(0, s5.compareTo(studentAscending[4]));
		
		studentSorter.sort(studentDescending);
		assertEquals(0, s1.compareTo(studentDescending[0]));
		assertEquals(0, s2.compareTo(studentDescending[1]));
		assertEquals(0, s3.compareTo(studentDescending[2]));
		assertEquals(0, s4.compareTo(studentDescending[3]));
		assertEquals(0, s5.compareTo(studentDescending[4]));
		
		studentSorter.sort(studentRandom);
		assertEquals(0, s1.compareTo(studentRandom[0]));
		assertEquals(0, s2.compareTo(studentRandom[1]));
		assertEquals(0, s3.compareTo(studentRandom[2]));
		assertEquals(0, s4.compareTo(studentRandom[3]));
		assertEquals(0, s5.compareTo(studentRandom[4]));
	}
	
	/**
	 * Tests QuickSorter using MiddleELementSelector
	 */
	@Test
	public void testSortMiddleElementSelector() {
		studentSorter = new QuickSorter<Student>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		
		Student s1 = new Student("Jason", "Alastair", 4410, 13, 3.45, "jalastair");
		Student s2 = new Student("Alfred", "Smith", 4404, 15, 3.82, "asmith");
		Student s3 = new Student("Colby", "Smith", 4392, 14, 2.99, "csmith");
		Student s4 = new Student("Colby", "Smith", 4399, 16, 4.00, "csmith2");
		Student s5 = new Student("David", "Sweasey", 4400, 13, 4.00, "dbswease");
		
		Student[] studentAscending = { s1, s2, s3, s4, s5 };
		Student[] studentDescending = { s5, s4, s3, s2, s1 };
		Student[] studentRandom = {s3, s5, s2, s1, s4};
		
		studentSorter.sort(studentAscending);
		assertEquals(0, s1.compareTo(studentAscending[0]));
		assertEquals(0, s2.compareTo(studentAscending[1]));
		assertEquals(0, s3.compareTo(studentAscending[2]));
		assertEquals(0, s4.compareTo(studentAscending[3]));
		assertEquals(0, s5.compareTo(studentAscending[4]));
		
		studentSorter.sort(studentDescending);
		assertEquals(0, s1.compareTo(studentDescending[0]));
		assertEquals(0, s2.compareTo(studentDescending[1]));
		assertEquals(0, s3.compareTo(studentDescending[2]));
		assertEquals(0, s4.compareTo(studentDescending[3]));
		assertEquals(0, s5.compareTo(studentDescending[4]));
		
		studentSorter.sort(studentRandom);
		assertEquals(0, s1.compareTo(studentRandom[0]));
		assertEquals(0, s2.compareTo(studentRandom[1]));
		assertEquals(0, s3.compareTo(studentRandom[2]));
		assertEquals(0, s4.compareTo(studentRandom[3]));
		assertEquals(0, s5.compareTo(studentRandom[4]));
	}
}