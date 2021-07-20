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

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public Node<V> getNext(int key) {
        return this.key > key ? this.left : this.right;
    }

}
