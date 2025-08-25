package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K> , V> implements Map61B<K, V>{
    private int size;

    private BSTNode<K, V> root;

    private static class BSTNode<K extends Comparable<K> , V> {
        BSTNode<K, V> left;
        BSTNode<K, V> right;
        K key;
        V val;

        public BSTNode() {
            left = null;
            right = null;
            key = null;
            val = null;
        }

        public BSTNode(K k) {
            left = null;
            right = null;
            key = k;
            val = null;
        }

        public BSTNode(K k, V v) {
            left = null;
            right = null;
            key = k;
            val = v;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    private BSTNode<K, V> find(K key) {
        return find(root, key);
    }

    private BSTNode<K, V> find(BSTNode<K, V> T, K key) {
        if(T == null){
            return null;
        }
        if(T.key.equals(key)) {
            return T;
        }

        if (T.key.compareTo(key) > 0) {
            return find(T.left, key);
        } else {
            return find(T.right, key);
        }
    }

    public void printInOrder() {
        return;
    }

    private BSTNode<K, V> insert(K ik) {
        return insert(root, ik);
    }

    private BSTNode<K, V> insert(BSTNode<K, V> T, K ik) {
        BSTNode<K, V> newT = new BSTNode<>(ik);
        if(T == null) {
            T = new BSTNode<>(ik);
        }
        if(T.key.compareTo(ik) > 0) {
            return insert(T.left, ik);
        } else if(T.key.compareTo(ik) < 0) {
            return insert(T.right, ik);
        }
        size += 1;
        return newT;
    }

    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return !(find(key) == null);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        BSTNode<K,V> node = get(root, key);
        return (node == null) ? null : node.val;
    }

    private BSTNode<K,V> get(BSTNode<K,V> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        }
        else if (cmp > 0) {
            return get(node.right, key);
        }
        else {
            return node;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        root = put(root, key, value);
    }

    private BSTNode<K, V> put(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new BSTNode<K, V>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.val = value;
        }
        return node;
    }

    //no requirement
    public Set<K> keySet() {
        throw new UnsupportedOperationException("This method has not implemented yet");
    }

    //no requirement
    public V remove(K key) {
        throw new UnsupportedOperationException("This method has not implemented yet");
    }

    //no requirement
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("This method has not implemented yet");
    }


    //no requirement
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("This method has not implemented yet");
    }

}
