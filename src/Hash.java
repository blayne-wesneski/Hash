import java.util.Iterator;
import java.util.LinkedList;

public class Hash<K, V> implements /* HashI<K, V> */ Iterable<K> {
    // PRIVATE INNER CLASS
    class HashElement<K, V> implements Comparable<HashElement<K, V>> {

        public K key;
        public V value;

        public HashElement(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(final HashElement<K, V> obj) {
            return ((Comparable<K>) this.key).compareTo(obj.key);
        }

        @Override
        public String toString() {
            return "[ " + this.key + " : " this.value + " ]";
        }
    }

    class IteratorHelper implements Iterator<K> {
        int counter;

        public IteratorHelper() {
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            return this.counter < Hash.this.elements;
        }

        @Override
        public K next() {
            return Hash.this.table[this.counter++].key;
        }
    }

    @Override
    public Iterator<K> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    private int numElements;
    private int tableSize;
    private double maxLoadFactor;
    private LinkedList<HashElement<K, V>>[] table;
    private final boolean DEBUG = true;

    public Hash(final int tableSize) {
        this.tableSize = tableSize;

        table = (linkedList<HashElement<K, V>>[]) new LinkedList[tableSize];

        // init the linked lists
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<HashElement<K, V>>();
        }

        this.maxLoadFactor = 5;
        this.numElements = 0;
    }

    public double loadFactor() {
        if (DEBUG)
            System.out.println("LoadFactor=" + (double) numElements / (double) table.length);
        return (double) numElements / (double) table.length;
    }

    public boolean add(K key, V value) {
        if (DEBUG) {
            System.out.println("Add K=" + key + " and V=" + value);
        }

        // is full?
        if (maxLoadFactor < loadFactor()) {
            // TODO: resize...
            return false;
        }

        // inti a hash element
        HashElement<K, V> element = new HashElement<K, V>(key, value);
        if (DEBUG) {
            System.out.println("Element: " + element);
        }

        // Hash
        // Get num
        int hashval = key.hashCode();
        if (DEBUG) {
            System.out.println("(1) Hashval)= " + hashval);
        }
        // ensure it's positive
        hashval = hashval & 0x7FFFFFFF;
        if (DEBUG) {
            System.out.println("(2) Hashval= " + hashval);
        }
        // limit to
        hashval = hashval % tableSize;
        if (DEBUG) {
            System.out.println("(3) Hashval= " + hashval);
        }

        return true;
    }
}
