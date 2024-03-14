package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author David Sweasey
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {
	
	/** The positional list which contains the map entries */
    private PositionalList<Entry<K, V>> list;
    
    /**
     * Constructs a new UndorderedLinkedMap which is a PositionalLinkedList of
     * Entries
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    /**
     * Iterates over the positional list and attempts to find an element with a matching
     * key. If found, return the position. If not, returns null. Moves the lookedUp element
     * to the front in order to improve the average cost.
     * 
     * @param key the key of the element to search for
     * @return the position of the key, or null if it does not exist
     */
    private Position<Entry<K, V>> lookUp(K key)
    {
    	// Creating an iterator to traverse the list
        Iterable<Position<Entry<K, V>>> it = list.positions();
        for (Position<Entry<K, V>> p: it) {
        	if (p.getElement().getKey().equals(key)) {
        		moveToFront(p);
        		return p;
        	}
        }
        return null;
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if (p == null) {
        	return null;
        }
        return p.getElement().getValue();
    }
    
    /**
     * Helper method which moves an element that was just looked up to the
     * front in order to improve costs. Follows the Map ADT move-to-front heuristic.
     * 
     * @param position the position of the element to move to the front
     */
    private void moveToFront(Position<Entry<K, V>> position) {
    	list.remove(position);
    	list.addFirst(position.getElement());
    }

    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
    	Entry<K, V> entry = new MapEntry<K, V>(key, value);
        if (p == null) {
        	list.addFirst(entry);
        	return null;
        } else {
        	V toRtn = list.remove(list.first()).getValue();
        	list.addFirst(entry);
        	return toRtn;
        }
        
    }
    
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if (p == null) {
    	   return null;
       }
       V toRtn = list.remove(list.first()).getValue();
       return toRtn;
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}