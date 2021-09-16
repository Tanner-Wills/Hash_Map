import java.util.NoSuchElementException;

/**
 * Implementation of a ExternalChainingHashMap.
 * Dictionary where each entry is the start of a linked list
 */
public class ExternalChainingHashMap<K, V> {

    public static final int INITIAL_CAPACITY = 13;
    public static final double MAX_LOAD_FACTOR = 0.67;

    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     * <p>
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     * <p>
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     * <p>
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     * <p>
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        // Invalid argument
        if (key == null || value == null)
            throw new IllegalArgumentException();

        // Get hashCode
        int hashCode = Math.abs(key.hashCode() % table.length);

        // Check if key in table and add if necessary
        V searchVal = searchIndex(key, value, table[hashCode]);

        // Found Case - Return replaced value
        if (searchVal != null) {
            return searchVal;
        }

        // Add new entry at index
        else {
            // Resize if necessary
            if ((size + 1.0) / table.length > MAX_LOAD_FACTOR) {
                resizeBackingTable(2 * table.length + 1);
                put(key, value);
            } else {
                table[hashCode] = new ExternalChainingMapEntry<K, V>(key, value, table[hashCode]);
                size++;
            }
            return null;
        }
    }

    /**
     * Removes the entry with a matching key from the map.
     * @param key The key to remove.
     * @return The value associated with the key.
     */
    public V remove(K key) {
        if (key == null)
            throw new IllegalArgumentException();
        //Get HashCode
        int hashCode = Math.abs(key.hashCode() % table.length);
        // Search for key
        ExternalChainingMapEntry<K, V> curVal = table[hashCode];
        ExternalChainingMapEntry<K, V> prevVal = null;
        while (curVal != null) {
            // If match
            if (curVal.getKey().equals(key)) {
                // If only value
                if (prevVal == null) {
                    table[hashCode] = curVal.getNext();
                }
                // If other than first value
                else {
                    prevVal.setNext(curVal.getNext());
                }
                size--;
                return curVal.getValue();
            }
            // If not at current index
            else {
                prevVal = curVal;
                curVal = curVal.getNext();
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Helper method stub for resizing the backing table to length.
     * <p>
     * This method should be called in put() if the backing table needs to
     * be resized.
     * <p>
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     * <p>
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     * <p>
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param Length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // Create new table
        ExternalChainingMapEntry<K, V>[] temp = table;
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];
        size = 0;
        // Add all values
        for (int i = 0; i < temp.length; i++) {
            ExternalChainingMapEntry<K, V> curVal = temp[i];
            while (curVal != null) {
                put(curVal.getKey(), curVal.getValue());
                curVal = curVal.getNext();
            }
        }
    }

    /**
     * Returns the table of the map.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    // Helper Methods
    private V searchIndex(K key, V value, ExternalChainingMapEntry<K, V> indexPos) {
        // Not Found Case
        if (indexPos == null)
            return null;

            // Found Case
        else if (indexPos.getKey().equals(key)) {
            // Save old value and overwrite
            V returnVal = indexPos.getValue();
            indexPos.setValue(value);
            return returnVal;
        }
        // Continue Recursion
        else {
            return searchIndex(key, value, indexPos.getNext());
        }
    }
}
