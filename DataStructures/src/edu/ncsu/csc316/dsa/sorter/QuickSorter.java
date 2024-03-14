package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    /**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
    
    /**
     * Tracks the PivotSelector to use for the QuickSort algorithm
     */
    private PivotSelector selector;
	
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * Sets the selector to use for this QuickSorter
     * 
     * @param selector PivotSelector to use for QuickSort
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }
	
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
	}
	
	/**
	 * Private method used for doing quicksort. Because this algorithm is recursive, a
	 * public-private pair is used. Finds a pivot location within the provided bounds,
	 * finds its location, and then quick sorts either side of the pivot.
	 * 
	 *  @param data the data to sort
	 *  @param low the lowest index currently being sorted
	 *  @param high the highest index currently being sorted
	 */
	private void quickSort(E[] data, int low, int high) {
		if (low < high) {
			int pivotLoc = partition(data, low, high);
			quickSort(data, low, pivotLoc - 1);
			quickSort(data, pivotLoc + 1, high);
		}
	}
	
	/**
	 * Helper method which selects a pivot, swaps it to be the last element
	 * in the array, and then returns the correct index for the pivot
	 * 
	 * @param data the data to sort
	 * @param low the lowest index currently being sorted 
	 * @param high the highest index currently being sorted
	 * @return the index of the pivot
	 */
	private int partition(E[] data, int low, int high) {
		int pivotIdx = selector.selectPivot(low, high);
		swap(data, pivotIdx, high);
		return partitionHelper(data, low, high);
	}
	
	/**
	 * Helper method which determines the correct position of the pivot and puts 
	 * all elements that should be before the pivot before it and the same for elements
	 * after the pivot
	 * 
	 * @param data the data to sort
	 * @param low the lowest index currently being sorted
	 * @param high the highest index currently being sorted 
	 * @return the index of the pivot
	 */
	private int partitionHelper(E[] data, int low, int high) {
		E pivot = data[high];
		int pivotIdx = low;
		for (int i = low; i < high; i++) {
			if (!(compare(data[i], pivot) > 0)) {
				swap(data, pivotIdx, i);
				pivotIdx++;
			}
		}
		swap(data, pivotIdx, high);
		return pivotIdx;
	}
	
	/**
	 * Helper method which swaps two elements of the inputed array
	 * 
	 * @param data the data to swap elements in
	 * @param first the index of the first element to be swapped
	 * @param second the index of the second element to be swapped
	 */
	private void swap(E[] data, int first, int second) {
		E temp = data[second];
		data[second] = data[first];
		data[first] = temp;
	}

	/**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * LastElementSelector chooses the last index of the array as the index of the 
     * pivot element that should be used when sorting
     * 
     * @author David Sweasey
     */
    public static class LastElementSelector implements PivotSelector {
    	
    	@Override
    	public int selectPivot(int low, int high) {
    		return high;
    	}
    }
    
    /**
     * MiddleElementSelector chooses the middle index (high + low / 2) of the array as 
     * the index of the pivot element that should be used when sorting 
     * 
     * @author David Sweasey
     */
    public static class MiddleElementSelector implements PivotSelector {

		@Override
		public int selectPivot(int low, int high) {
			return (high + low) / 2;
		}
    }
    
    /**
     * RandomElementSelector chooses a random index of the array as the index of
     * the pivot element that should be used when sorting
     * 
     * @author David Sweasey
     */
    public static class RandomElementSelector implements PivotSelector {
    	
    	@Override
    	public int selectPivot(int low, int high) {
    		return (int) (Math.random() * (high - low + 1) + low);
    	}
	}
}
