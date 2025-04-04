package src.chap2;
// 순차검색

import java.security.Key;
import java.util.ArrayList;

class Node<K,V> {
    K key;
    V value;
    Node<K,V> next;

    public Node(K key, V value, Node<K,V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
public class SequentialSeachST<K,V>{
    private Node<K,V> first;
    int N;
    // 시험 전에 직접 구현 해보기 p11 ~ p13
    public V get(K key) {
        for(Node<K,V> x = first; x != null; x = x.next) {
            if(key.equals(x.key))
                return x.value;
        }
        return null;
    }
    public void put(K key,V value) {
        for(Node<K,V> x = first; x != null; x = x.next){
            if(key.equals(x.key)) {
                x.value = value;
                return;
            }
            first = new Node<K,V>(key,value,first);
            N++;
        }
    }
    public void delete(K key) {
        if (key.equals(first.key)) {
            first =first.next;
            N--;
            return;
        }
        for(Node<K,V> x = first; x != null; x = x.next){
            if(key.equals(x.next.key)) { // point!
                x.next = x.next.next;
                N--;
                return;
            }
        }
    }
    public Iterable<K> keys() {
        ArrayList<K> keylist = new ArrayList<K>(N);
        for(Node<K,V> x = first; x != null; x = x.next) {
            keylist.add(x.key);
        }
        return keylist;
    }
    public boolean contains(K key) {return get(key) != null;}
    public boolean isEmpty() {return N == 0;}

    public int size() {return N;}


}
