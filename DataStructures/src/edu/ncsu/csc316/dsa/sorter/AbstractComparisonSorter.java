package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Enumerates the functionality of comparison sorter classes. Comparison based
 * sorting algorithms use a custom comparator, or the natural order. Referenced the
 * workshop 1 website
 * 
 * @author David Sweasey
 *
 * @param <E> the generic type
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	
	/** Stores the comparator used by the sorting algorithm */
	private Comparator<E> comparator;
	
	/**
	 * Constructor for a comparison sorter algorithm. Takes a custom comparator and
	 * applies it to the algorithm. If null is the parameter, the natural order
	 * is used by default.
	 * 
	 * @param comparator comparator used by the algorithm
	 */
	public AbstractComparisonSorter(Comparator<E> comparator) {
		setComparator(comparator);
	}
	
	/**
	 * Sets the comparator to the sorting algorithm. If null, sets the comparator
	 * to the natural order.
	 * 
	 * @param comparator the comparator to set
	 */
	private void setComparator(Comparator<E> comparator) {
		if (comparator == null) {
			this.comparator = new NaturalOrder();
		} else {
			this.comparator = comparator;
		}
	}
	
	/**
	 * Uses the comparator to compare to values and return an integer. A negative
	 * integer indicates that data1 comes before data2. A positive integer indicates
	 * that data2 comes before data1. A 0 indicates the two are equal
	 * 
	 * @param data1 first element to compare
	 * @param data2 second element to compare
	 * @return an integer describing the relative order of the two elements
	 */
	public int compare (E data1, E data2) {
		return comparator.compare(data1, data2);
	}
	
	/**
	 * Private class describes the natural ordering of objects, as described by their class
	 * Class was used from the Workshop 1 website
	 *  
	 * @author Dr. King
	 * @author David Sweasey
	 */
	private class NaturalOrder implements Comparator<E> {
		public int compare(E first, E second) {
			return ((Comparable<E>) first).compareTo(second);
		}
	}
}
