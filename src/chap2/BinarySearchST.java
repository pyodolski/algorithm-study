package src.chap2;

import java.util.ArrayList;

// p16 - p20
public class BinarySearchST<K extends Comparable<K>, V> {
    private static final int INT_CAPACITY = 10;
    private K[] keys;
    private V[] values;
    private int N;

    public BinarySearchST() {
        keys = (K[]) new Comparable[INT_CAPACITY];
        values = (V[]) new Object[INT_CAPACITY];
    }

    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity]; // 배열의 최대 용량 보다 많은 용량?
    }
    private void resize(int capacity) { // 동적으로 배열 크기 변경
        K[] tmpkeys = (K[]) new Comparable[capacity];
        V[] tmpvals = (V[]) new Object[capacity];
        for (int i = 0; i < N; i++)
        {
            tmpkeys[i] = keys[i];
            tmpvals[i] = values[i];
        }
        values = tmpvals;
        keys = tmpkeys;
    }
    private int search(K key)
    {
        int lo = 0;
        int hi = N - 1;
        while (lo < hi){
            int mid = (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo; // 키가 없으면 lo 반환
    }
    public V get(K key){
        if(isEmpty()) return null;
        int i = search(key);
        if (i < N && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    public void put(K key, V value) {
        int i = search(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value; return;
        }
        if(N == keys.length) resize(2*keys.length);

        for(int j = N; j > i; j--)
        {
            keys[j] = keys[j -1];
            values[j] = values[j - 1];
        }
        keys[i] = key; values[i] = value; N++;
    }
    public void delete(K key) {
        if (isEmpty()) return;
        int i = search(key);
        if (i < N && keys[i].compareTo(key) != 0) {
            return;
        }

        for(int j = i; j < N - 1; j++){
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        N--;
        keys[N] = null; values[N] = null;
        if ( N > INT_CAPACITY && N == keys.length/4)
            resize(keys.length/2);
    }

    public  Iterable<K> keys() {
        ArrayList<K> keyLisy = new ArrayList<K>(N);
        for (int i = 0; i < N; i++)
            keyLisy.add(keys[i]);
        return keyLisy;
    }
    public boolean contains(K key) {return get(key) != null;}
    public boolean isEmpty() { return N == 0;}
    public int size() {return N;}


}
