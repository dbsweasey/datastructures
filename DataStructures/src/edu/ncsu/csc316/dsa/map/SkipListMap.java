package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    /**
     * Helper method to determine if an entry is one of the sentinel
     * -INFINITY or +INFINITY nodes (containing a null key)
     * 
     * @param node the node to check
     * @return whether or not the node is a sentinel node
     */
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    /**
     * A helper method used in order to find an entry based on its key to 
     * then be used in other map operations
     * 
     * @param key the key to find
     * @return the node with the provided key
     */
    private SkipListNode<K, V> lookUp(K key) {
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        if (isSentinel(temp) || compare(temp.getEntry().getKey(), key) != 0) {
        	return null;
        }
        return temp.getEntry().getValue();
    }

    /**
     * Helper method for the put operation method that inserts a new node above the
     * current node.
     * 
     * @param prev the node previous to the new above node
     * @param down the current node 
     * @param entry the entry to be stored in the node
     * @return the new node
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        if (prev != null) {
        	newNode.setNext(prev.getNext());
        	newNode.getPrevious().setNext(newNode);
        }
        if (newNode.getNext() != null) {
        	newNode.getNext().setPrevious(newNode);
        }
        if (down != null) {
        	down.setAbove(newNode);
        }
        return newNode;
    }

    @Override
    public V put(K key, V value) {
        SkipListNode<K, V> temp = lookUp(key);
        // If an entry with this key already exists
        if (!isSentinel(temp) && compare(temp.getEntry().getKey(), key) == 0) {
        	V toRtn = temp.getEntry().getValue();
        	// Replace all entries above (with the same key) with the new value
        	while (temp != null) {
        		temp.setEntry(new MapEntry<K, V>(key, value));
        		temp = temp.getAbove();
        	}
        	return toRtn;
        }
        SkipListNode<K, V> newEntry = null;
        int currentLevel = -1;
        do {
        	currentLevel++;
        	if (currentLevel >= height) {
        		height++;
        		SkipListNode<K, V> tail = start.getNext();
        		start = insertAfterAbove(null, start, null);
        		insertAfterAbove(start, tail, null);
        	}
        	newEntry = insertAfterAbove(temp, newEntry, new MapEntry<K, V>(key, value));
        	while (temp.getAbove() == null) {
        		temp = temp.getPrevious();
        	}
        	temp = temp.getAbove();
        } while (coinToss.nextBoolean());
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        if (isSentinel(temp)) {
        	return null;
        }
        V toRtn = temp.getEntry().getValue();
        do {
        	temp.getPrevious().setNext(temp.getNext());
        	temp.getNext().setPrevious(temp.getPrevious());
        	temp = temp.getAbove();
        } while (temp != null);
    	size--;
    	while (start.below != null && isSentinel(start.below.getNext())) {
    		start = start.below;
    		height--;
    	}
    	return toRtn;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * This method may be useful for testing or debugging.
     * You may comment-out this method instead of testing it, since
     * the full string will depend on the series of random coin flips
     * and will not have deterministic expected results.
     * 
     * @return the full SkipListMap string
     */
    public String toFullString() {
        StringBuilder sb = new StringBuilder("SkipListMap[\n");
        SkipListNode<K, V> cursor = start;
        SkipListNode<K, V> firstInList = start;
        while (cursor != null) {
            firstInList = cursor;
            sb.append("-INF -> ");
            cursor = cursor.next;
            while (cursor != null && !isSentinel(cursor)) {
                sb.append(cursor.getEntry().getKey() + " -> ");
                cursor = cursor.next;
            }
            sb.append("+INF\n");
            cursor = firstInList.below;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * A quad node used for SkipLists. Has references to the node above, below, 
     * before, and after. 
     * 
     * @author David Sweasey
     * @author Dr. King
     *
     * @param <K> the key of the node
     * @param <V> the the value stored at the node
     */
    private static class SkipListNode<K, V> {

    	/** The entry stored at the node */
        private Entry<K, V> entry;
        
        /** Reference to the node above this */
        private SkipListNode<K, V> above;
        
        /** Reference to the node below this */
        private SkipListNode<K, V> below;
        
        /** Reference to the node before this */
        private SkipListNode<K, V> prev;
        
        /** Reference to the node after this */
        private SkipListNode<K, V> next;

        /**
         * Constructor for a SkipListNode. Sets the entry of the node to the
         * provided entry. Sets all other references to null
         * 
         * @param entry the entry to be stored in a node
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        /**
         * Returns the node above this
         * 
         * @return the above node
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }

        /**
         * Returns the entry stored at this node
         * 
         * @return the entry
         */
        public Entry<K, V> getEntry() {
            return entry;
        }

        /**
         * Returns the node after this
         * 
         * @return the node after
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }

        /**
         * Returns the node previous to this
         * 
         * @return the previous node
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }

        /**
         * Sets the node above this to the provided node
         *  
         * @param up the node to set above
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        /**
         * Sets the node below this to the provided node
         * 
         * @param down the node to set below
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        /**
         * Sets the entry to be stored in this node
         * 
         * @param entry the entry to store
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * Sets the node after this to the provided node
         * 
         * @param next the node to set after
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        /**
         * Sets the node previous to this to the provided node
         * 
         * @param prev the node to set before
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
}