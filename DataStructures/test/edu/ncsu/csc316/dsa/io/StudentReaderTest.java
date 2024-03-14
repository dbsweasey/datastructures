package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for StudentReader
 * 
 * @author David Sweasey
 * @author Dr. King
 */
public class StudentReaderTest {
	
	/**
	 * Tests the ability of StudentReader to read in students in the correct order
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/**
	 * Tests the ability of StudentReader to read one line and process the data accurately
	 */
	@Test
	public void testProcessLine() {
		Student[] contents = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		Student s1 = contents[3];
		assertEquals("Idalia", s1.getFirst());
		assertEquals("Pease", s1.getLast());
		assertEquals("peasei", s1.getUnityID());
		assertEquals(5, s1.getId());
		assertEquals(2.72, s1.getGpa(), 0.0001);
		assertEquals(9, s1.getCreditHours());
	}
	
	/**
	 * Tests that an exception is thrown if a file cannot be read from
	 */
	@Test
	public void testFileNotFound() {
		assertThrows(IllegalArgumentException.class, 
				() -> StudentReader.readInputAsArray("thisfiledoesnotexist.txt"));
	}
}
