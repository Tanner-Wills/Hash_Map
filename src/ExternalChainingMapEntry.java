/**
 * Map entry class used for implementing the ExternalChainingHashMap.
 */
public class ExternalChainingMapEntry<K, V> {

    private K key;
    private V value;
    private ExternalChainingMapEntry<K, V> next;

    /**
     * Constructs a new ExternalChainingMapEntry with only the given key and value.
     */
    public ExternalChainingMapEntry(K key, V value) {
        this(key, value, null);
    }

    /**
     * Constructs a new ExternalChainingMapEntry with the given key, value, and next reference.
     */
    public ExternalChainingMapEntry(K key, V value, ExternalChainingMapEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * Gets the key.
     */
    public K getKey() {
        return key;
    }

    /**
     * Gets the value.
     */
    public V getValue() {
        return value;
    }

    /**
     * Gets the next entry.
     */
    public ExternalChainingMapEntry<K, V> getNext() {
        return next;
    }

    /**
     * Sets the key.
    */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Sets the value.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Sets the next entry.
     */
    public void setNext(ExternalChainingMapEntry<K, V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String key = this.key == null ? "null" : this.key.toString();
        String value = this.value == null ? "null" : this.value.toString();
        return String.format("(%s, %s)", key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        // DO NOT USE THIS METHOD IN YOUR CODE! This is for testing ONLY!
        if (!(o instanceof ExternalChainingMapEntry)) {
            return false;
        } else {
            ExternalChainingMapEntry<K, V> that = (ExternalChainingMapEntry<K, V>) o;
            return that.getKey().equals(key) && that.getValue().equals(value);
        }
    }
}