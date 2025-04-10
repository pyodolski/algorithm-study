// 학번 : 22213489 이름 : 표주원
package org.example;

import java.io.*;
import java.util.*;

public class HW2 {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("첫번째 파일 이름? ");
        String fileA = consoleReader.readLine();
        System.out.print("두번째 파일 이름? ");
        String fileB = consoleReader.readLine();

        BST<String, Integer> bstA = new BST<>();
        BST<String, Integer> bstB = new BST<>();

        int countA = buildShingleTree(fileA, bstA);
        int countB = buildShingleTree(fileB, bstB);

        int intersection = 0;
        int union = 0;

        Set<String> allKeys = new HashSet<>();
        for (String k : bstA.keys()) allKeys.add(k);
        for (String k : bstB.keys()) allKeys.add(k);

        for (String k : allKeys) {
            Integer valA = bstA.get(k);
            Integer valB = bstB.get(k);
            if (valA != null && valB != null) {
                intersection += Math.min(valA, valB);
                union += Math.max(valA, valB);
            } else if (valA != null) {
                union += valA;
            } else if (valB != null) {
                union += valB;
            }
        }

        double similarity = (union == 0) ? 0.0 : (double) intersection / union;

        System.out.println("파일 " + fileA + "의 Shingle의 수 = " + countA);
        System.out.println("파일 " + fileB + "의 Shingle의 수 = " + countB);
        System.out.println("두 파일에서 공통된 shingle의 수 = " + intersection);
        System.out.println(fileA + "과 " + fileB + "의 유사도 = " + similarity);
    }

    public static int buildShingleTree(String filename, BST<String, Integer> bst) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(" ");
        }
        reader.close();

        StringTokenizer tokenizer = new StringTokenizer(sb.toString(), " \t\n=;,<>()");
        List<String> words = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            words.add(tokenizer.nextToken());
        }

        int count = 0;
        for (int i = 0; i <= words.size() - 5; i++) {
            String shingle = String.join(" ", words.subList(i, i + 5));
            Integer existing = bst.get(shingle);
            if (existing == null) {
                bst.put(shingle, 1);
            } else {
                bst.put(shingle, existing + 1);
            }
            count++;
        }

        return count;
    }
}

class BST<K extends Comparable<K>, V> {
    protected Node<K, V> root;

    public int size() {
        return (root != null) ? root.N : 0;
    }

    public V get(K key) {
        if (root == null) return null;
        Node<K, V> x = treeSearch(key);
        if (key.equals(x.key)) return x.value;
        else return null;
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node<K, V>(key, val);
            return;
        }
        Node<K, V> x = treeSearch(key);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.value = val;
        else {
            Node<K, V> newNode = new Node<K, V>(key, val);
            if (cmp < 0) x.left = newNode;
            else x.right = newNode;
            newNode.parent = x;
            rebalanceInsert(newNode);
        }
    }

    protected Node<K, V> treeSearch(K key) {
        Node<K, V> x = root;
        while (true) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp < 0) {
                if (x.left == null) return x;
                else x = x.left;
            } else {
                if (x.right == null) return x;
                else x = x.right;
            }
        }
    }

    protected void rebalanceInsert(Node<K, V> x) {
        resetSize(x.parent, 1);
    }

    protected void rebalanceDelete(Node<K, V> p, Node<K, V> deleted) {
        resetSize(p, -1);
    }

    private void resetSize(Node<K, V> x, int value) {
        for (; x != null; x = x.parent)
            x.N += value;
    }

    public Iterable<K> keys() {
        if (root == null) return new ArrayList<>();
        ArrayList<K> keyList = new ArrayList<>(size());
        inorder(root, keyList);
        return keyList;
    }

    private void inorder(Node<K, V> x, ArrayList<K> keyList) {
        if (x != null) {
            inorder(x.left, keyList);
            keyList.add(x.key);
            inorder(x.right, keyList);
        }
    }

    public void delete(K key) {
        if (root == null) return;
        Node<K, V> x, y, p;
        x = treeSearch(key);
        if (!key.equals(x.key)) return;
        if (x == root || isTwoNode(x)) {
            if (isLeaf(x)) {
                root = null;
                return;
            } else if (!isTwoNode(x)) {
                root = (x.right == null) ? x.left : x.right;
                root.parent = null;
                return;
            } else {
                y = min(x.right);
                x.key = y.key;
                x.value = y.value;
                p = y.parent;
                relink(p, y.right, y == p.left);
                rebalanceDelete(p, y);
            }
        } else {
            p = x.parent;
            if (x.right == null)
                relink(p, x.left, x == p.left);
            else if (x.left == null)
                relink(p, x.right, x == p.left);
            rebalanceDelete(p, x);
        }
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    protected boolean isLeaf(Node<K, V> x) {
        return x.left == null && x.right == null;
    }

    protected boolean isTwoNode(Node<K, V> x) {
        return x.left != null && x.right != null;
    }

    protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
        if (child != null) child.parent = parent;
        if (makeLeft) parent.left = child;
        else parent.right = child;
    }

    protected Node<K, V> min(Node<K, V> x) {
        while (x.left != null) x = x.left;
        return x;
    }

    public K min() {
        if (root == null) return null;
        Node<K, V> x = root;
        while (x.left != null) x = x.left;
        return x.key;
    }

    public K max() {
        if (root == null) return null;
        Node<K, V> x = root;
        while (x.right != null) x = x.right;
        return x.key;
    }

    public K floor(K key) {
        if (root == null || key == null) return null;
        Node<K, V> x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node<K, V> floor(Node<K, V> x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node<K, V> t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public int rank(K key) {
        if (root == null || key == null) return 0;
        Node<K, V> x = root;
        int num = 0;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                num += 1 + size(x.left);
                x = x.right;
            } else {
                num += size(x.left);
                break;
            }
        }
        return num;
    }

    public int size(Node<K, V> x) {
        return (x == null) ? 0 : x.N;
    }

    public K select(int rank) {
        if (root == null || rank < 0 || rank >= size()) return null;
        Node<K, V> x = root;
        while (true) {
            int t = size(x.left);
            if (rank < t) x = x.left;
            else if (rank > t) {
                rank = rank - t - 1;
                x = x.right;
            } else return x.key;
        }
    }
}

class Node<K, V> {
    K key;
    V value;
    Node<K, V> left, right, parent;
    int N = 1;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
