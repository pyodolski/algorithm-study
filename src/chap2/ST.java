package src.chap2;

import java.util.LinkedList;
import java.util.Queue;

public class ST<Key, Value> {
    private Node first; // 연결 리스트의 첫 노드

    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    // 값 저장 (존재하면 업데이트, 없으면 삽입)
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val; // 이미 존재하면 값 업데이트
                return;
            }
        }
        first = new Node(key, val, first); // 새로운 노드를 맨 앞에 삽입
    }

    // 값 조회
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;
        }
        return null; // 존재하지 않으면 null
    }

    // 키 존재 여부 확인
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // 키 리스트 반환 (반복문에 사용 가능)
    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();
        for (Node x = first; x != null; x = x.next) {
            q.add(x.key);
        }
        return q;
    }
}