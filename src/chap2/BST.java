import java.util.ArrayList;

class Node<K,V>{
    K key;
    V value;
    int N;
    int aux;
    Node<K,V> left;
    Node<K,V> right;
    Node<K,V> parent;
    public Node(K key, V value){
        this.key = key;
        this.value = value;
        this.N = 1;
    }
    public int getAux() {return aux;}
    public void setAux(int aux) {this.aux = aux;}

}
public class BST <K extends Comparable<K>, V> {
    protected Node<K,V> root;
    protected Node<K,V> treeSearch(K key) {
        Node<K,V> x = root;
        while(true) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0)  return x;
            else if (cmp < 0) x = x.left;
            else {
                if (x.right == null) return x;
                else x = x.right;
            }
        }
    }

    public V get(K key) {
        if (root == null) return null;
        Node<K,V> x = treeSearch(key);
        if (key.equals(x.key)) return x.value;
        else return null;
    }

    public void put(K key, V value) {
        if (root == null) {root = new Node<K,V>(key, value); return;}
        Node<K,V> x = treeSearch(key);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.value = value;
        else {
            Node<K, V> newNode = new Node<K, V>(key, value);
            if (cmp < 0) x.left = newNode;
            else x.right = newNode;
            newNode.parent = x;
            rebalanceInsert(newNode);
        }
    }
    protected void rebalanceInsert(Node<K,V> x) {
        resetSize(x.parent,1);
    }
    protected void resetSize(Node<K,V> x, int delta) {
        for(;x!=null; x = x.parent) x.N += delta;
    }
    protected void rebalanceDelete(Node<K,V> p, Node<K,V> deleted) {
        resetSize(p, -1);
    }
    public Iterable<K> keys() {
        if (root == null) return null;
        ArrayList<K> keylist = new ArrayList<K>(size(x.left));
        inorder(root,keylist);
        return keylist;
    }
    protected void inorder(Node<K,V> x, ArrayList<K> keylist) {
        if (x == null) return;
        inorder(x.left,keylist);
        keylist.add(x.key);
        inorder(x.right,keylist);
    }
    public void delete(K key) {
        if(root == null) return;
        Node<K,V> x, y, p;
        x = treeSearch(key);
        if (!key.equals(x.key)) {
            return;
        }
        if (x==root || isTwoNode(x)) {
            if (isLeaf(x)) {
                root = null;
                return;
            } // 루트이고 단일 노드일 경우
            else if (!isTwoNode(x)) {
                root = (x.right == null) ? x.left : x.right;
                root.parent = null;
                return; // 루트인데 자식 한개
            } else {
                y = min(x.right); // inorder successor
                x.key = y.key;
                x.value = y.value;
                p = y.parent;
                relink(p, y.right, y == p.left);
                rebalanceDelete(p, y); // 자식이 둘인 노드
            }
        }
           else {
               p = x.parent;
               if(x.right == null)
                relink(p,x.left,x == p.left);
               else if(x.left == null)
                relink(p,x.right,x == p.left);
               rebalanceDelete(p,x);
            }
        }

    public boolean contains(K key) {return get(key) != null;}
    public boolean isEmpty() {return root == null;}
    public int size(Node<K, V> left) {return root == null ? 0 : root.N;}
    protected boolean isTwoNode(Node<K,V> x) {
        return x.left != null && x.right != null;
    }
    protected boolean isLeaf(Node<K,V> x) {
        return x.left == null && x.right == null;
    }
    protected Node<K,V> min(Node<K,V> x) {
        if (x.left == null) return x;
        else return min(x.left);
    }
    protected Node<K,V> max(Node<K,V> x) {
        if (x.right == null) return x;
        else return max(x.right);
    }
    protected void relink(Node<K,V> p, Node<K,V> child, boolean isLeft) {
        if(child != null) child.parent = p;
        if(isLeft) p.left = child;
        else p.right = child;
    }
    public K max() {
        if(root == null) return null;
        Node<K,V> x = root;
        while(x.right != null) x = x.right;
        return x.key;
    }
    private Node<K,V> floor(Node<K,V> x, K key) {
        if (root == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node<K,V> t = floor(x.right, key); // ???? 중요
        if (t != null) return t;
        else return x;
    }
    public K floor(K key) {
        Node<K,V> x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }
    public int rank(K key) {
        if(root == null|| key == null) return 0;
        Node<K,V> x = root;
        int rank = 0;
        while(x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                rank += 1 + size(x.left);
                x = x.right;
            }
            else {
                rank += size(x.left);
                break;
            }
        }
        return rank;
    }
    public K select(int rank) {
        if (root == null) return null;
        Node<K,V> x = root;
        while(true) {
            int leftSize = size(x.left);
            if (leftSize > rank) x = x.left;
            else if (leftSize < rank) {
                rank = rank - leftSize -1;
                x = x.right;
            }
            else break;
        }
        return x.key;
    }

}
