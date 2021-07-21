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

    public Node<V> getNode(int key) {
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
        return getNode(key) != null;
    }

    public void delete(int key) {
        if (isRoot(key))
            throw new IllegalArgumentException("Cannot delete root of a tree");

        Node<V> node = getNode(key);
        Node<V> parent = getParent(key);
        switch (node.getNChildren()) {
            case 0 -> parent.replaceChild(node, null);

            case 1 -> {
                Node<V> child = (node.getLeft() == null) ? node.getRight() : node.getLeft();
                parent.replaceChild(node, child);
            }

            case 2 -> {
                Node<V> rightMinimumNode = node.getRightSubtreeMinimum();
                delete(rightMinimumNode.getKey());

                rightMinimumNode.setLeft(node.getLeft());
                rightMinimumNode.setRight(node.getRight());

                parent.replaceChild(node, rightMinimumNode);
            }
        }
    }

    public Node<V> getParent(int nodeKey) {
        Node<V> cursor = root;
        while (!cursor.isLeaf())
            if (cursor.getLeft().getKey() == nodeKey || cursor.getRight().getKey() == nodeKey)
                return cursor;
            else {
                cursor = cursor.getNext(nodeKey);
            }

        return null;
    }

    public boolean isRoot(int key) {
        return getNode(key).equals(root);
    }
}
