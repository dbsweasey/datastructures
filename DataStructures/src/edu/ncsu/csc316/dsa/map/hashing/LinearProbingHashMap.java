package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/** The hashing map is represented as an array of TableEntries. */
    private TableEntry<K, V>[] table;
    /** The size of the hash map */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
        for (int i = 0; i < table.length; i++) {
            if (!isAvailable(i)) {
            	collection.add(table[i]);
            }
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return table[index] == null || table[index].isDeleted();
    }

    @Override
    public V bucketGet(int hash, K key) {
        int index = findBucket(hash, key);
        if (index < 0) {
        	return null;
        }
        return table[index].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        int index = findBucket(hash, key);
        if (index >= 0) {
        	V toRtn = table[index].getValue();
        	table[index].setValue(value);
        	return toRtn;
        } 
        table[index * -1 - 1] = new TableEntry<K, V>(key, value);
        size++;
        return null;
    }

    private int findBucket(int index, K key) {
        int avail = -1;
        int j = index;
        do {
        	if (isAvailable(j)) {
        		if (avail == -1) {
        			avail = j;
        		}
        		if (table[j] == null) {
        			return -1 * (avail + 1);
        		}
        	} else if (table[j].getKey().equals(key)) {
        		return j;
        	}
        	j = (j + 1) % table.length;
        } while (j != index);
        return -1 * (avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
        int index = findBucket(hash, key);
        if (index < 0) {
        	return null;
        }
        V toRtn = table[index].getValue();
        table[index].setDeleted(true);
        size--;
        return toRtn;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    /**
     * Private class which defines what is stored at each element of the array.
     * Is essentially a MapEntry which contains a key, value, and a field
     * describing if the entry has been deleted or not. 
     * 
     * @author Jason King
     * @author David Sweasey
     *
     * @param <K> generic type of the key 
     * @param <V> generic type of the value
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	
    	/** Keeps track if an entry was deleted so a new hashed value can be stored here */
        private boolean isDeleted;

        /**
         * Constructor for a TableEntry, which is a MapEntry with a separate field
         * that describes if the entry has been deleted or not
         * 
         * @param key the key to set
         * @param value the value to set
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * Returns whether or not the entry has been deleted
         * 
         * @return the isDeleted field
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /**
         * Sets the isDeleted field to describe if the TableEntry has been deleted
         * 
         * @param deleted what to set isDeleted to
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}
