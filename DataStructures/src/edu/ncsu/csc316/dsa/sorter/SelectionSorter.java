package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/** 
	 * Default constructor of the Selection Sorter
	 * 
	 * @param comparator the comparator to use
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Parameterless constructor for selection sorter. Defaults the comparator
	 * to the natural ordering of the object
	 */
	public SelectionSorter() {
		this(null);
	}

	@Override
	public void sort(E[] data) {
		for (int i = 0; i < data.length; i++) {
			int min = i;
			for (int j = i + 1; j < data.length; j++) {
				if (compare(data[j], data[min]) < 0) {
					min = j;
				}
			}
			if (min != i) {
				E temp = data[i];
				data[i] = data[min];
				data[min] = temp;
			}
		}
	}
}
