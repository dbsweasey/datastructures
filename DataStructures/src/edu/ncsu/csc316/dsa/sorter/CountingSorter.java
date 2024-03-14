package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
	
	@Override
	public void sort(E[] data) {
		int min = data[0].getId();
		int max = data[0].getId();
		for (int i = 0; i < data.length; i++) {
			min = Math.min(data[i].getId(), min);
			max = Math.max(data[i].getId(), max);
		}
		int range = max - min + 1;
		int count[] = new int[range];
		for (int i = 0; i < data.length; i++) {
			count[data[i].getId() - min] = count[data[i].getId() - min] + 1;
		}
		
		for (int i = 1; i < range; i++) {
			count[i] = count[i - 1] + count[i];
		}
		
		@SuppressWarnings("unchecked")
		E[] sorted = (E[]) (new Identifiable[data.length]);
		for (int i = data.length - 1; i >= 0; i--) {
			sorted[ count[ data[i].getId() - min ] - 1 ] = data[i];
			count[ data[i].getId() - min] = count[ data[i].getId() - min ] - 1;
		}
		
		// Puts the sorted elements in the original array
		for (int i = 0; i < data.length; i++) {
			data[i] = sorted[i];
		}
	}
	
}
