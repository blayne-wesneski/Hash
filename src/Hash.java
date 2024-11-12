import java.util.Iterator;
import java.util.LinkedList;

public class Hash<K, V> implements /* HashI<K, V> */ Iterable<K> {
    // PRIVATE INNER CLASS
    class HashElement<K, V> implements Comparable<HashElement<K, V>> {

        public K key;
        public V value;

        public HashElement(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(final HashElement<K, V> obj) {
            return ((Comparable<K>) this.key).compareTo(obj.key);
        }

        @Override
        public String toString() {
            return "HashElement [key=" + this.key + ", value=" + this.value + "]";
        }
    }

    class IteratorHelper<T> implements Iterator<T> {
        T[] keys;
        int position;

        public IteratorHelper() {
            keys = (T[]) new Object[numElements];

            // get everything out of the hash and put into an array of keys
            int p = 0;
            for (int i = 0; i < tableSize; i++) {
                LinkedList<HashElement<K, V>> list = table[i];

                for (HashElement<K, V> h : list) {
                    keys[p++] = (T) h.key;
                }
            }
            // init position
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < keys.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            return keys[position++];
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new IteratorHelper<K>();
    }

    private int numElements;
    private int tableSize;
    private double maxLoadFactor;
    private LinkedList<HashElement<K, V>>[] table;
    private final boolean DEBUG = true;

    public Hash(int tableSize) {
        this.tableSize = tableSize;

        table = (LinkedList<HashElement<K, V>>[]) new LinkedList[tableSize];

        // init the linked lists
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<HashElement<K, V>>();
        }

        this.maxLoadFactor = 0.5;
        this.numElements = 0;
    }

    public double loadFactor() {
        if (DEBUG)
            System.out.println("loadFactor = " + ((double) numElements / (double) table.length));
        return (double) numElements / (double) table.length;

    }

    public boolean add(K key, V value) {

        if (DEBUG)
            System.out.println("Add K=" + key + " and V=" + value);

        if (maxLoadFactor < loadFactor()) {
            // TODO: resize...
            return false;
        }

        HashElement<K, V> element = new HashElement<>(key, value);
        if (DEBUG)
            System.out.println("Element: " + element);

        int hashval = key.hashCode();
        if (DEBUG)
            System.out.println("hashval = " + hashval);
        hashval = hashval & 0x7FFFFFFF;
        if (DEBUG)
            System.out.println("hashval = " + hashval);
        hashval = hashval % tableSize;
        if (DEBUG)
            System.out.println("hashval = " + hashval);

        table[hash(key)].addLast(element);

        numElements++;
        return true;
    }

    private int hash(K key) {
        int hashval = key.hashCode();
        if (DEBUG)
            System.out.println("hashval = " + hashval);
        hashval = hashval & 0x7FFFFFFF;
        if (DEBUG)
            System.out.println("hashval = " + hashval);
        hashval = hashval % tableSize;
        if (DEBUG)
            System.out.println("hashval = " + hashval);

        return hashval;
    }

    public boolean remove(K key) {
        int hashval = hash(key);

        table[hashval].removeLast();

        return true;

    }

    public V getValue(K key) {
        int hashval = hash(key);

        for (HashElement<K, V> el : table[hashval]) {
            if (((Comparable<K>) key).compareTo(el.key) == 0) {
                return el.value;
            }
        }

        return null;

    }
}
