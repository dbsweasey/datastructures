package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author David Sweasey
 * @param <E> the generic type
 */
public interface Sorter<E> {
	
	/**
	 * Sorts a provided array of integers
	 * 
	 * @param data the array to sort
	 */
	void sort(E[] data);
}
