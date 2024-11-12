
public interface HashI<K, V> {
	/**
	 * Adds an element to the Hash table
	 * 
	 * @param key 
	 * @param value 
	 * @return
	 */
	boolean add(K key, V value);
	
	/**
	 * Removes an element from the Hash table
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	boolean remove(K key, V value);
	
	/**
	 * Returns a value associated with a key
	 * 
	 * @param key
	 * @return
	 */
	V getValue(K key);
	
	/**
	 * Change the size of the Hash table and rehash all existing
	 * elements into the new Hash table 
	 * 
	 * @param newSize
	 */
	void resize(int newSize);
	
	/**
	 * Return the load factor of the Hash table
	 * 
	 * @return
	 */
	double loadFactor();
}
