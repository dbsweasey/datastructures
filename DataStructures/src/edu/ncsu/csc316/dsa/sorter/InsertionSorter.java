package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author David Sweasey
 * @param <E> the generic type
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Default constructor that sets a null comparator
	 */
	public InsertionSorter() {
		super(null);
	}
	
	/**
	 * Constructor of InsertionSorter that uses a comparator passed as a parameter
	 * 
	 * @param comparator comparator to use for insertion sorting
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	
	@Override
	public void sort(E[] data) {
		for (int i = 1; i < data.length; i++) {
			E temp = data[i];
			int j = i - 1;
			while (j >= 0 && compare(data[j], temp) > 0) {
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = temp;
		}
		
	}
}
