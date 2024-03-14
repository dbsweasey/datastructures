package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		PositionalNode<E> prev = validate(p);
		return addBetween(element, prev.getNext(), prev);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		PositionalNode<E> next = validate(p);
		return addBetween(element, next, next.getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {
		return addBetween(element, front.getNext(), front);
	}

	@Override
	public Position<E> addLast(E element) {
		return addBetween(element, tail, tail.getPrevious());
	}
	
	/**
	 * Helper method which adds a new positional node between two specified nodes.
	 * 
	 * @param element the data contained within the new node
	 * @param next the next positional node
	 * @param prev the previous positional node
	 * @return the position of the new node
	 */
	private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
		PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev);
		prev.setNext(newNode);
		next.setPrevious(newNode);
		size++;
		return newNode;
	}

	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> pos = validate(p);
		if (pos == tail.getPrevious()) {
			return null;
		}
		return pos.getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> pos = validate(p);
		if (pos == front.getNext()) {
			return null;
		}
		return pos.getPrevious();
	}

	@Override
	public Position<E> first() {
		if (front.getNext() == tail) {
			return null;
		}
		return front.getNext();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		if (tail.getPrevious() == front) {
			return null;
		}
		return tail.getPrevious();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> pos = validate(p);
		E toRtn = pos.getElement();
		PositionalNode<E> prev = pos.getPrevious();
		PositionalNode<E> next = pos.getNext();
		prev.setNext(next);
		next.setPrevious(prev);
		size--;
		return toRtn;
	}

	@Override
	public E set(Position<E> p, E element) {
		PositionalNode<E> pos = validate(p);
		E toRtn = pos.getElement();
		pos.setElement(element);
		return toRtn;
	}

	@Override
	public int size() {
		return size;
	}

	  /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
	
	/**
	 * Represents a node within a positional list. Each node has a value, a reference to the next node in the list, and a reference to the previous node in the list
	 * 
	 * @author David Sweasey
	 * @author Dr. King
	 * @param <E> the generic type
	 */
    private static class PositionalNode<E> implements Position<E> {

    	/** The element stored within the node */
        private E element;
        
        /** Reference to the next node in the list */
        private PositionalNode<E> next;
        
        /** Reference to the previous node in the list */
        private PositionalNode<E> previous;

        /**
         * PositionalNode constructor where only the element to be stored is known
         * 
         * @param value the element to be stored in the node
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * PositionalNode constructor where the element and the next reference are known
         * 
         * @param value the element to be stored in the node
         * @param next the next reference
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * PositionalNode constructor where the element, the next reference, and the previous reference are known.
         * 
         * @param value the element to be stored in the node
         * @param next the next reference
         * @param prev the previous reference
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Sets the previous field with a reference to a PositionalNode
         * 
         * @param prev the node to set
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Returns the previous node in the list
         * 
         * @return the previous field
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Sets the next field with a reference to a PositionalNode
         * 
         * @param next the node to set
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Returns the next node in the list
         * 
         * @return the next field
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the element field of a particular node
         * 
         * @param element the element to set
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Iterates over a positional list. Stores and returns positions of the list.
     * 
     * @author David Sweasey
     */
    private class PositionIterator implements Iterator<Position<E>> {

    	/** The current position of the iterator */
        private Position<E> current;
        
        /** A flag representing whether its possible to remove a position */
        private boolean removeOK;

        /**
         * Constructor for PositionIterator
         */
        public PositionIterator() {
            current = front;
            removeOK = false;
        }

        @Override
        public boolean hasNext() {
            PositionalNode<E> curr = validate(current);
            return curr.getNext().getElement() != null;
        }

        @Override
        public Position<E> next() {
            if (!hasNext()) throw new NoSuchElementException();
            PositionalNode<E> curr = validate(current);
            current = curr.getNext();
            removeOK = true;
            return current;
        }

        @Override
        public void remove() {
            if (!removeOK) throw new IllegalStateException();
            PositionalLinkedList.this.remove(current);
            removeOK = false;
        }
    }
    
    /**
     * Wrapper class enabling PositionIterator to be returned as an Iterable object
     * 
     * @author Dr. King
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
    
    /**
     * Iterates over a positional list. Stores and returns elements of the list.
     * 
     * @author David Sweasey
     */
    private class ElementIterator implements Iterator<E> {

    	/** Stores the PositionIterator that helps with the element iterator */
        private Iterator<Position<E>> it;

        /**
         * Constructor for the ElementIterator
         */
        public ElementIterator() {
            it = new PositionIterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
}