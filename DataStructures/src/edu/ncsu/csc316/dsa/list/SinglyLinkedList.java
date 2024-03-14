package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		if (index == 0) {
			front = new LinkedListNode<E>(element, front);
			if (size == 0) {
				tail = front;
			}
		} else if (index == size) {
			tail.setNext(new LinkedListNode<E>(element));
			tail = tail.getNext();
		} else {
			LinkedListNode<E> curr = front;
			for (int i = 0; i < index - 1; i++) {
				curr = curr.getNext();
			}
			curr.setNext(new LinkedListNode<E>(element, curr.getNext()));
		}
		size++;
		
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		LinkedListNode<E> curr = front;
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}
		return curr.getElement();
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		E toRtn;
		if (index == 0) {
			toRtn = front.getElement();
			front = front.getNext();
		} else {
			LinkedListNode<E> curr = front;
			for (int i = 0; i < index - 1; i++) {
				curr = curr.getNext();
			}
			toRtn = curr.getNext().getElement();
			if (index == size - 1) {
				tail = curr;
				tail.setNext(null);
			} else {
				curr.setNext(curr.getNext().getNext());
			}
		}
		size--;
		return toRtn;
	}

	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		LinkedListNode<E> curr = front;
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}
		E toRtn = curr.getElement();
		curr.setData(element);
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
	 * The static nested class which enumerates the functionality of LinkedListNodes.
	 * Each node contains data and a reference to the next node in the list.
	 * 
	 * @author David Sweasey
	 *
	 * @param <E> the generic type
	 */
    private static class LinkedListNode<E> {
    	/** Stores the data of a node */
    	private E data;
    	/** Stores the next reference of a node */
    	private LinkedListNode<E> next;
    	
    	/**
    	 * Constructor for a LinkedListNode where only the data is known
    	 * 
    	 * @param data the data stored in the node
    	 */
    	public LinkedListNode(E data) {
    		this(data, null);
    	}
    	
    	/**
    	 * Constructor for a LinkedListNode with data and a next reference
    	 * 
    	 * @param data the data stored in the node
    	 * @param next the next LinkedListNode reference
    	 */
    	public LinkedListNode(E data, LinkedListNode<E> next) {
    		setData(data);
    		setNext(next);
    	} 
    	
    	/**
    	 * Returns the next LinkedListNode 
    	 * 
    	 * @return the next reference
    	 */
    	public LinkedListNode<E> getNext() {
    		return next;
    	}
    	
    	/**
    	 * Returns the data at the current node
    	 * 
    	 * @return the data field
    	 */
    	public E getElement() {
    		return data;
    	}
    	
    	/**
    	 * Sets the next reference of this linked node 
    	 * 
    	 * @param next the next node
    	 */
    	public void setNext(LinkedListNode<E> next) {
    		this.next = next;
    	}
    	
    	/**
    	 * Sets the data of this linked node
    	 * 
    	 * @param data the data to set
    	 */
    	public void setData(E data) {
    		this.data = data;
    	}
    }
    
    /**
     * ElementIterator allows for the traversal forwards and backwards through a SinglyLinkedList
     * 
     * @author David Sweasey
     *
     */
    private class ElementIterator implements Iterator<E> {
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        /** 
         * Keep track of the node that was processed on the last call to 'next'
         */
        private LinkedListNode<E> previous;
        
//      /**
//       * Keep track of whether it's ok to remove an element (based on whether
//       * next() has been called immediately before remove())
//       */
//      private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            current = front;
            previous = null;
        }

        @Override
        public boolean hasNext() {
            return current != null && current.getElement() != null;
        }

        @Override
        public E next() {
        	if (!hasNext()) throw new NoSuchElementException();
            previous = current;
            current = current.getNext();
            return previous.getElement();
        }
         
        @Override    
        public void remove() {
            throw new UnsupportedOperationException(
                "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
        }
    }
}