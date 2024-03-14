package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	@Override
	public void sort(E[] data) {
		int max = 0;
		for (int i = 0; i < data.length; i++) {
			max = Math.max(max, data[i].getId());
		}
		int place = (int) Math.ceil(Math.log10(max + 1));
		
		int currPlace = 1;
		for (int j = 1; j <= place; j++) {
			int[] digits = new int[10];
			for (int i = 0; i < data.length; i++) {
				digits[ (data[i].getId() / currPlace) % 10 ] = digits[ (data[i].getId() / currPlace) % 10] + 1;
			}
			
			for (int i = 1; i < 10; i++) {
				digits[i] = digits[i - 1] + digits[i];
			}
			
			@SuppressWarnings("unchecked")
			E[] sorted = (E[]) (new Identifiable[data.length]);
			for (int i = data.length - 1; i >= 0; i--) {
				sorted[ digits[ (data[i].getId() / currPlace) % 10] - 1] = data[i];
				digits[ (data[i].getId() / currPlace) % 10] = digits[ (data[i].getId() / currPlace) % 10] - 1;
			}
			
			for (int i = 0; i < data.length; i++) {
				data[i] = sorted[i];
			}
			
			currPlace *= 10;
		}
	}
	
	
}
