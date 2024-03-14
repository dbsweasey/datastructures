package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;

/**
 * A skeletal implementation of the Tree abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the tree
 * abstract data type.
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <E> the type of elements stored in the tree
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public E set(Position<E> p, E value) {
    	AbstractTreeNode<E> pos;
        try {
        	pos = validate(p);
        } catch(IllegalArgumentException e) {
        	throw new IllegalArgumentException();
        }
        E toRtn = pos.getElement();
        pos.setElement(value);
        return toRtn;
        
    }   
    
    @Override
    public Iterable<Position<E>> preOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }

    /**
     * Helper method for the pre-order traversal method. 
     * 
     * @param p the current position to of the traversal algorithm
     * @param traversal the position collection to add elements pre-order to
     */
    private void preOrderHelper(Position<E> p, PositionCollection traversal) {
        traversal.add(p);
        for (Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }  
    
    @Override
    public Iterable<Position<E>> postOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
        	postOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    /**
     * Helper method for the post-order traversal method. 
     * 
     * @param p the current position to of the traversal algorithm
     * @param traversal the position collection to add elements post-order to
     */
    private void postOrderHelper(Position<E> p, PositionCollection traversal) {
    	for (Position<E> c : children(p)) {
    		postOrderHelper(c, traversal);
    	}
    	traversal.add(p);
    }
    
    @Override
    public Iterable<Position<E>> levelOrder() {
        PositionCollection traversal = new PositionCollection();
    	if (!isEmpty()) {
    		levelOrderHelper(traversal);
    	}
    	
        return traversal;
    }
    
    /**
     * Helper method for the level order traversal method. 
     *
     * @param traversal the position collection to add elements to
     */
    private void levelOrderHelper(PositionCollection traversal) {
    	Queue<Position<E>> q = new ArrayBasedQueue<Position<E>>();
    	q.enqueue(root());
        while (!q.isEmpty()) {
        	Position<E> p = q.dequeue();
        	traversal.add(p);
        	for (Position<E> c : children(p)) {
        		q.enqueue(c);
        	}
        }
    }
    
    
    /**
     * Safely casts a Position, p, to be an AbstractTreeNode.
     * 
     * @param p the position to cast to a AbstractTreeNode
     * @return a reference to the AbstractTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  AbstractTreeNode
     */
    protected abstract AbstractTreeNode<E> validate(Position<E> p);
    
    /**
     * Enumerates the behaviors of an AbstractTreeNode, which is a node used in a tree. Each node stores an element.
     * 
     * @author David Sweasey
     * @author Dr. King
     *
     * @param <E> the type of the node
     */
    protected abstract static class AbstractTreeNode<E> implements Position<E> {

    	/** The element stored within the node */
        private E element;
        
        /**
         * Constructs an AbstractTreeNode with an element
         * 
         * @param element the value stored in the node
         */
        public AbstractTreeNode(E element) {
            setElement(element);
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets the value within the node
         * 
         * @param element the value to be stored in the node
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Helps construct a string which represents the Tree
     * 
     * @param sb the StringBuilder object used to construct a string
     * @param indent a string of whitespace so that the constructed string accurately represents levels
     * @param root the root of the tree
     */
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * PositionCollection implements the {@link Iterable} interface to allow traversing
     * through the positions of the tree. PositionCollection does not allow removal
     * operations
     * 
     * @author David Sweasey
     * @author Dr. King
     *
     */
    protected class PositionCollection implements Iterable<Position<E>> {

    	/** A list of positions to store the PositionCollections, an iterable set of positions for traversals. */
        private List<Position<E>> list;

        /**
         * Construct a new PositionCollection
         */
        public PositionCollection() {
            list = new SinglyLinkedList<Position<E>>();
        }

        /**
         * Add a position to the collection. Null positions or positions with null
         * elements are not added to the collection
         * 
         * @param position the position to add to the collection
         */
        public void add(Position<E> position) {
            if (position != null && position.getElement() != null) {
                list.addLast(position);
            }
        }

        /**
         * Return an iterator for the PositionCollection
         * 
         * @return iterator for PositionCollection
         */
        public Iterator<Position<E>> iterator() {
            return new PositionSetIterator();
        }

        /**
         * Describes and implements the behaviors of an iterator over PositionSCollection
         * 
         * @author Dr. King
         * @author David Sweasey
         */
        private class PositionSetIterator implements Iterator<Position<E>> {

        	/** The PositionSet iterator */
            private Iterator<Position<E>> it;

            /**
             * Constructor for PositionSetIterator
             */
            public PositionSetIterator() {
                it = list.iterator();
            }

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Position<E> next() {
                return it.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        }
    }
}