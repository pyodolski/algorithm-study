package src.chap3;


import java.util.ArrayList;

public class SeparateChainingHashST <K,V>{
    private int N;
    private int M;
    private SeparateChainingHashST<K,V>[] st; // 순차 연결 리스트의 배열로 테이블 구성

    public SeparateChainingHashST() { this(997); }
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SeparateChainingHashST<K,V>[]) new SeparateChainingHashST[M];
        for (int i = 0; i < M; i++) st[i] = new SeparateChainingHashST<K,V>();
    }
    public boolean contains(K key) { return get(key) != null; }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    private int hash(K key) { return (key.hashCode() & 0x7fffffff) % M; }
    public V get(K key) {return st[hash(key)].get(key);}
    public void delete(K key) {
        if (!contains(key)) return;
        st[hash(key)].delete(key);
        N--;
    }
    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<K>();
        for (int i = 0; i < M; i++)
            for(K key : st[i].keys())
                keyList.add(key);
        return keyList;
    }
}
