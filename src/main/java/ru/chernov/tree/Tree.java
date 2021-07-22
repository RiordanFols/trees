package ru.chernov.tree;

import lombok.Getter;

import java.util.*;

/**
 * @author Pavel Chernov
 */
@Getter
public class Tree<V> {

    private final Node<V> root;

    public Tree(Node<V> root) {
        this.root = root;
    }

    public Tree(Collection<Node<V>> nodes) {
        if (nodes.size() < 2)
            throw new IllegalArgumentException("Tree should contains at least 2 nodes");

        List<Node<V>> list = new ArrayList<>(nodes);
        list.sort(Comparator.comparing(Node::getKey));
        this.root = list.get(list.size() / 2);
        nodes.remove(this.root);

        for (var node : nodes)
            put(node.getKey(), node.getValue());
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
        Node<V> cursor = this.root;
        while (cursor.getKey() != key && cursor.getNext(key) != null)
            cursor = cursor.getNext(key);

        // update
        if (cursor.getKey() == key) {
            cursor.setValue(value);
            return;
        }

        if (cursor.getNext(key) == null) {
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
                Node<V> rightMinimumNode = node.getRightSubtreeMinimumNode();
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

    public void goThrow() {
        Scanner scanner = new Scanner(System.in);
        Node<V> cursor = this.root;
        while (cursor != null && !cursor.isLeaf()) {
            System.out.println(cursor.getKey());
            String command = scanner.next();
            switch (command.trim()) {
                case "0" -> cursor = cursor.getLeft();
                case "1" -> cursor = cursor.getRight();
                default -> System.out.println("Enter 0 or 1");
            }
        }
        if (cursor == null)
            System.out.println("No node here");
        else
            System.out.println("You get to leaf: " + cursor.getKey());
    }

    public int getHeight() {
        return this.root.getHeightOfSubtree();
    }

    @Override
    public String toString() {
        return TreeToStringConverter.getAsArray(this);
    }

    public String toStringAsTree() {
        return TreeToStringConverter.getAsTree(this);
    }
}
