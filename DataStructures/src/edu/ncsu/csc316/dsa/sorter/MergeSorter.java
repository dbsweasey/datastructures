package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] data) {
		if (data.length <= 1) {
			return;
		}
		int mid = data.length / 2;
		E[] left = Arrays.copyOfRange(data, 0, mid);
		E[] right = Arrays.copyOfRange(data, mid, data.length);
		
		sort(left);
		sort(right);
		
		merge(left, right, data);
	}
	
	/**
	 * Combines the left and right sorted arrays into the data output array. Helper method for sort()
	 * 
	 * @param left the left sorted array
	 * @param right the right sorted array
	 * @param data the output array
	 */
	private void merge(E[] left, E[] right, E[] data) {
		int leftIdx = 0;
		int rightIdx = 0;
		while (leftIdx + rightIdx < data.length) {
			if (rightIdx == right.length || leftIdx < left.length && compare(left[leftIdx], right[rightIdx]) < 0) {
				data[leftIdx + rightIdx] = left[leftIdx];
				leftIdx++;
			} else {
				data[leftIdx + rightIdx] = right[rightIdx];
				rightIdx++;
			}
		}
	}
}