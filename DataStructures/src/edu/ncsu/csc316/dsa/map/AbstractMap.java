package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * MapEntry implements the Entry abstract data type.
     * 
     * @author Dr. King
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {

    	/** The key of the element. Used by the lookUp() to find the correct entry */
        private K key;
        
        /** THe value stored within the entry */
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value
         * 
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key
         * 
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value
         * 
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }       
    }
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     * @author David Sweasey
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {
    	/** Field for the singly linked list which stores entries */
    	 private List<Entry<K, V>> list;

    	 /**
    	  * EntryCollection constructor makes a new SinglyLinkedList of entries
    	  */
         public EntryCollection() {
             list = new SinglyLinkedList<Entry<K, V>>();
         }

         /**
          * Adds an entry to the EntryCollection
          * 
          * @param entry the entry to add to the collection
          */
         public void add(Entry<K, V> entry) {
             list.addLast(entry);
         }

         /**
          * Creates an iterator to traverse the EntryCollection
          * 
          * @return an EntryCollectionIterator
          */
         public Iterator<Entry<K, V>> iterator() {
             return new EntryCollectionIterator();
         }

         /**
          * Outlines the functionality of the EntryCollectionIterator, which travers
          * a SinglyLinkedList of entries. Used by KeyIterator and ValueIterator
          * to traverse the entries based on keys and values respectively. 
          * 
          * @author Dr. King
          * @author David Sweasey
          */
         private class EntryCollectionIterator implements Iterator<Entry<K, V>> {

        	 /** An iterator used to traverse a collection of entries */
             private Iterator<Entry<K, V>> it;

             /**
              * Constructor for the EntryCollectionIterator. It is simply a
              * SinglyLinkedList iterator
              */
             public EntryCollectionIterator() {
                 it = list.iterator();
             }

             @Override
             public boolean hasNext() {
                 return it.hasNext();
             }

             @Override
             public Entry<K, V> next() {
                 return it.next();
             }

             @Override
             public void remove() {
                 throw new UnsupportedOperationException("The remove operation is not supported yet.");
             }
         } 
    }       

    /**
     * KeyIterator implements the {@link Iterator} interface to allow traversing
     * through the keys stored in the map
     * 
     * @author Dr. King
     * @author David Sweasey
     *
     */
    protected class KeyIterator implements Iterator<K> {
    	/** The iterator used to iterate of the Map's keys */
    	private Iterator<Entry<K, V>> it;
        
    	/**
    	 * Constructs a KeyIterator by creating an entrySet iterator to use.
    	 */
        public KeyIterator() {
            it = entrySet().iterator();
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public K next() {
            return it.next().getKey();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        } 
    }

    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map
     * 
     * @author Dr. King
     * @author David Sweasey
     *
     */
    protected class ValueIterator implements Iterator<V> {
    	/** The iterator used to iterate of the Map's keys */
    	private Iterator<Entry<K, V>> it;
        
    	/**
    	 * Constructs a ValueIterator by creating an entrySet iterator to use.
    	 */
        public ValueIterator() {
        	it = entrySet().iterator();
        }
        
        @Override
        public boolean hasNext() {
        	return it.hasNext();
        }
        
        @Override
        public V next() {
        	return it.next().getValue();
        }
        
        @Override
        public void remove() {
        	throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    /**
     * Private class used to wrap ValueIterator into an iterable object
     * 
     * @author Dr. King
     * @author David Sweasey
     */
    private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator() {
        	return new ValueIterator();
        }
    }
}