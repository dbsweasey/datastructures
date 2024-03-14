package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getGpa() > two.getGpa()) return -1;
		else if (one.getGpa() < two.getGpa()) return 1;
		return one.compareTo(two);
	}

}
