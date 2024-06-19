import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SortByBinaryTree {


    private static Node binaryTree;

    private static StringBuilder result;

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        String string = Files.readString(Paths.get(file.getPath()));
        List<Integer> numbers = Arrays.stream(string.split("\\s"))
                .map(o -> Integer.parseInt(o))
                .collect(Collectors.toList());
        for (int i : numbers) {
            if (i == 0) {
                break;
            }
            add(i);
        }
        result = new StringBuilder();
        sort(binaryTree);
        System.out.println(result.toString().trim());
    }

    public static void sort(Node node) {
        if (node != null) {
            sort(node.left);
            result.append(node.value + "\n");//обход с сортировкой
            sort(node.right);
        }
    }

    public static void add(int value) {
        Node node = new Node();
        node.value = value;
        if (binaryTree == null) {
            binaryTree = node;
        } else {
            Node currentNode = binaryTree;
            Node parentNode;
            while (currentNode != null) {
                parentNode = currentNode;
                if (currentNode.value == value) {
                    return;
                } else if (currentNode.value > value) {
                    currentNode = currentNode.left;
                    if (currentNode == null) {
                        parentNode.left = node;
                    }
                } else {
                    currentNode = currentNode.right;
                    if (currentNode == null) {
                        parentNode.right = node;
                    }
                }
            }
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public String toString() {
            return "" + value;
        }
    }
}
