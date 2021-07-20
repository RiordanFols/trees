package ru.chernov.tree;

/**
 * @author Pavel Chernov
 */
public class Main {

    public static void main(String[] args) {


        Node<String> rootLLR = new Node<>(4, "", null, null);
        Node<String> rootLL = new Node<>(1, "", null, rootLLR);
        Node<String> rootL = new Node<>(5, "", rootLL, null);

        Node<String> rootRLL = new Node<>(17, "You found me", null, null);
        Node<String> rootRLR = new Node<>(31, "", null, null);
        Node<String> rootRL = new Node<>(20, "", rootRLL, rootRLR);
        Node<String> rootRR = new Node<>(99, "", null, null);
        Node<String> rootR = new Node<>(35, "", rootRL, rootRR);

        Node<String> root = new Node<>(10, "Root", rootL, rootR);

        Tree<String> tree = new Tree<>(root);
//        System.out.println(tree.get(17).getValue());

        tree.put(42, "New element");
        System.out.println(tree.get(99).getLeft().getValue());

//        System.out.println(tree.getParentOfNode(20).getKey());
    }
}
