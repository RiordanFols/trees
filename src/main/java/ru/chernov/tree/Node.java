package ru.chernov.tree;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Pavel Chernov
 */
@Setter
@Getter
@EqualsAndHashCode(of = {"key"})
public class Node<V> {

    private final int key;
    private V value;
    private Node<V> left;
    private Node<V> right;

    public Node(int key, V value, Node<V> left, Node<V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(int key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node<V> getNext(int key) {
        return (this.key > key) ? this.left : this.right;
    }

    public boolean isLeaf() {
        return this.getNChildren() == 0;
    }

    public int getNChildren() {
        return (this.left != null ? 1 : 0) + (this.right != null ? 1 : 0);

    }

    public Node<V> getRightSubtreeMinimum() {
        Node<V> cursor = this.getRight();
        while (!cursor.isLeaf())
            cursor = cursor.getLeft();

        return cursor;
    }

    public void replaceChild(Node<V> child, Node<V> newChild) {
        if (this.getLeft().equals(child))
            this.setLeft(newChild);
        else
            this.setRight(newChild);
    }
}
