package ru.chernov.tree;

import lombok.Getter;

/**
 * @author Pavel Chernov
 */
@Getter
public class Tree<V> {

    private final Node<V> root;

    public Tree(Node<V> root) {
        this.root = root;
    }

    public Node<V> get(int key) {
        Node<V> cursor = root;
        while (cursor != null)
            if (cursor.getKey() == key)
                return cursor;
            else {
                cursor = cursor.getNext(key);
            }

        return null;
    }

    public void put(int key, V value) {
        Node<V> cursor = root;
        while (cursor.getKey() != key && !cursor.isLeaf())
            cursor = cursor.getNext(key);

        // update
        if (cursor.getKey() == key) {
            cursor.setValue(value);
            return;
        }

        if (cursor.isLeaf()) {
            Node<V> newNode = new Node<>(key, value, null, null);
            if (cursor.getKey() > key)
                cursor.setLeft(newNode);
            else
                cursor.setRight(newNode);
        }
    }

    public boolean contains(int key) {
        return get(key) != null;
    }

    public void delete(int key) {
        // todo:
        // cases:
        // if leaf
        // if has 1 child
        // if has 2 childes
    }

    public Node<V> getParentOfNode(int nodeKey) {
        Node<V> cursor = root;
        while (!cursor.isLeaf())
            if (cursor.getLeft().getKey() == nodeKey || cursor.getRight().getKey() == nodeKey)
                return cursor;
            else {
                cursor = cursor.getNext(nodeKey);
            }

        return null;
    }

}
