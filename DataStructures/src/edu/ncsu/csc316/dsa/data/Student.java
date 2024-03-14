package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** First name field of a student */
	private String first;
	/** Last name string of a student */
	private String last;
	/** ID number of the student */
	private int id;
	/** Number of credit hours of the student */
	private int creditHours;
	/** GPA of the student */
	private double gpa;
	/** The unityID of the student */
	private String unityID;
	
	/**
	 * Default constructor for a student object
	 * 
	 * @param first first name
	 * @param last last name
	 * @param id id number
	 * @param creditHours number of credit hours
	 * @param gpa the student's gpa
	 * @param unityID unityID of student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
	}

	/**
	 * Returns the first field of the student
	 * 
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the first name of the student
	 * 
	 * @param first the first name to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Returns the last field of the student
	 * 
	 * @return the last name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the last name of the student
	 * 
	 * @param last the last name to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Returns the id field of the student
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id number of the student
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the creditHours field of the student
	 * 
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Sets the number of credit hours for the student
	 * 
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Returns the gpa field of the student
	 * 
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets the gpa of the student
	 * 
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Returns the unityID field of the student
	 * 
	 * @return the unityID
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Sets the unityID of the student
	 * 
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * Generates and returns a hashcode of a student object based on its fields
	 * 
	 * @return a unique hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}

	
	/**
	 * Determines whether or not two objects are equal student objects. This is the
	 * case if and only if both objects are student objects, and both objects have
	 * identical values for first, last, and ID fields. Overridden to check equality
	 * with student-specific fields.
	 * 
	 * @param obj the object to compare
	 * @return a boolean describing if the objects are equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}
	
	/**
	 * Compares two student objects. First, checks which last name comes first alphabetically,
	 * then which first name comes alphabetically, then compares student ID numbers.
	 * If the first student should come before the parameter object, return -1. Otherwise
	 * returns 1.
	 * 
	 * @param s the student to be compared to
	 * @return 1 or -1 depending on which student should come first
	 */
	public int compareTo(Student s) {
		if (!last.equals(s.getLast())) {
			if (last.compareTo(s.getLast()) > 0) return 1;
			return -1;
		}
		if (!first.equals(s.getFirst())) {
			if (first.compareTo(s.getFirst()) > 0) return 1;
			return -1;
		}
		if (id != s.getId()) {
			if (id > s.getId()) return 1;
			return -1;
		}
		return 0;
	}

	/**
	 * Creates a string representation of a student object. Overridden to provide 
	 * toString functionality specifically to the student class.
	 * 
	 * @return the string representation
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}
}
