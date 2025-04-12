package src.chap3;

public class LinearProbingHashST <K,V>{
    private int N;
    private int M;
    private K[] keys;
    private V[] vals;

    public LinearProbingHashST() { this(31); }
    public LinearProbingHashST(int M) {
        keys = (K[]) new Object[M];
        vals = (V[]) new Object[M];
        this.M = M;
    }
    public boolean contains(K key) { return get(key) != null; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    private int hash(K key) { return (key.hashCode() & 0x7fffffff) % M; }
    public V get(K key) {
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if(keys[i].equals(key)) return vals[i]; // 순회하면서 맞는것이 있으면 가져옴
        return null;
    }
    public void put(K key, V val) {
        if (N >= M / 2) resize(2 * M + 1);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    private void resize(int cap) {
        LinearProbingHashST<K,V> temp = new LinearProbingHashST<K,V>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) temp.put(keys[i], vals[i]);
        }
        this.keys = temp.keys;
        this.vals = temp.vals;
        this.M = temp.M;
    }

    public void delete(K key) {
        if (!contains(key)) return;
    }
}
