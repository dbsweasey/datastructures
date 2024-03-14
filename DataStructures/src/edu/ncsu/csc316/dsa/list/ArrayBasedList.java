package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    
    @Override
    public void add(int index, E value) {
    	try {
    		checkIndexForAdd(index);
    	} catch (Exception e) {
    		throw new IndexOutOfBoundsException(e.getMessage());
    	}
    	if (data.length == size) {
    		ensureCapacity(size + 1);
    	}
    	if (index == 0 && size == 0) {
    		data[0] = value;
    	} else if (index == size) {
    		data[size] = value;
    	} else {
    		for (int i = size; i > index; i--) {
    			data[i] = data[i - 1];
    		}
    		data[index] = value;
    	}
    	size++;
    }

	@Override
	public E get(int index) {
    	try {
    		checkIndex(index);
    	} catch (Exception e) {
    		throw new IndexOutOfBoundsException(e.getMessage());
    	}
		return data[index];
	}

	@Override
	public E remove(int index) {
    	try {
    		checkIndex(index);
    	} catch (Exception e) {
    		throw new IndexOutOfBoundsException(e.getMessage());
    	}
		E toRtn = data[index];
		data[index] = null;
		// Perform left shift
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;
		return toRtn;
	}

	@Override
	public E set(int index, E element) {
    	try {
    		checkIndex(index);
    	} catch (Exception e) {
    		throw new IndexOutOfBoundsException(e.getMessage());
    	}
		E toRtn = data[index];
		data[index] = element;
		return toRtn;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}
	
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
     * ElementIterator allows for the traversal of the ArrayBased list. 
     * 
     * @author David Sweasey
     */
    private class ElementIterator implements Iterator<E> {
    	/** The current position of the iterator. Indicates which index it directly precedes */
        private int position;
        
        /** A flag determining whether an element can be removed. Can be removed if next was called without a remove after*/
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            position = 0;
            removeOK = false;
        }

        @Override
        public boolean hasNext() {
        	return position < size;
        }

        @Override
        public E next() {
        	if (!hasNext()) throw new NoSuchElementException();
        	removeOK = true;
        	position++;
            return data[position - 1];
        }
            
        @Override
        public void remove() {
            if (!removeOK) throw new IllegalStateException();
            ArrayBasedList.this.remove(position	- 1);
            position--;
            removeOK = false;
        }
    }
}