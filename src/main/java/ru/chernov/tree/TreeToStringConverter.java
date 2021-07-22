package ru.chernov.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Pavel Chernov
 */
public class TreeToStringConverter {

    public static <V> String getAsTree(Tree<V> tree) {
        var matrix = getMatrix(tree);
//        int lineLength = formatLineString(matrix.get(matrix.size() - 1)).length();
        StringBuilder treeString = new StringBuilder();
        for (int i = matrix.size() - 1; i >= 0; i--) {
            treeString.append(formatLineString(matrix.get(i))).append("\n");
        }

        return treeString.toString();
    }

    public static <V> String formatLineString(List<Node<V>> list) {
        return list.toString()
                .replaceAll("\\Q[\\E", "")
                .replaceAll("\\Q]\\E", "")
                .replaceAll(",", "");
    }

    public static <V> String getAsArray(Tree<V> tree) {
        return null;
    }

    private static <V> List<List<Node<V>>> getMatrix(Tree<V> tree) {
        int heightWithNull = tree.getHeight() + 1;
        List<List<Node<V>>> matrix = new ArrayList<>(heightWithNull);
        for (int i = 0; i < heightWithNull; i++)
            // fill with nulls
            matrix.add(new ArrayList<>(Collections.nCopies((int) Math.pow(2, i), null)));

        return putNodeIntoMatrix(matrix, tree.getRoot(), 0, 0);
    }

    private static <V> List<List<Node<V>>> putNodeIntoMatrix(List<List<Node<V>>> matrix, Node<V> node,
                                                             int indexY, int indexX) {
        matrix.get(indexY).set(indexX, node);

        if (node.hasLeft())
            putNodeIntoMatrix(matrix, node.getLeft(), indexY + 1, indexX * 2);

        if (node.hasRight())
            putNodeIntoMatrix(matrix, node.getRight(), indexY + 1, indexX * 2 + 1);

        return matrix;
    }
}
