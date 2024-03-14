/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * BubbleSorter uses the bubble sort algorithm to sort data
 *
 * @author David Sweasey
 * @param <E> the generic type
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E>  {

	/**
	 * Default constructor that sets a null comparator
	 */
	public BubbleSorter() {
		this(null);
	}
	
	/**
	 * Constructor of BubbleSorter that uses a comparator passed as a parameter
	 * 
	 * @param comparator comparator to use for bubble sorter
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	public void sort(E[] data) {
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 1; i < data.length; i++) {
				if (compare(data[i], data[i - 1]) < 0) {
					E temp = data[i - 1];
					data[i - 1] = data[i];
					data[i] = temp;
					swapped = true;
				}
			}
		}
	}

}
