import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BinaryTree {

    private static Node binaryTree;

    private static StringBuilder result;

    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String command = list.get(i);
            String[] data = command.split("\\s");
            if (command.startsWith("ADD")) {
                if (!add(Integer.parseInt(data[1]), false)) {
                    result.append("DONE" + "\n");
                } else {
                    result.append("ALREADY" + "\n");
                }
            } else if (command.startsWith("SEARCH")) {
                if (add(Integer.parseInt(data[1]), true)) {
                    result.append("YES" + "\n");
                } else {
                    result.append("NO" + "\n");
                }
            } else if (command.equals("PRINTTREE")) {
                Node node = binaryTree;
                StringBuilder builder = new StringBuilder();
                sort(node, builder);
                result.append(builder);
            }

        }
        System.out.println(result.toString().trim());
    }

    public static void sort(Node node , StringBuilder s) {
        if (node != null) {
            sort(node.left, s);
            String str = "";
            for (int i = 1; i < node.height; i++) {
                str += ".";
            }
            s.append(str+node.value + "\n");
            sort(node.right, s);
        }
    }

    public static boolean add(int value, boolean isSearch) {
        Node node = new Node();
        node.value = value;
        if (binaryTree == null && !isSearch) {
            binaryTree = node;
        } else {
            Node currentNode = binaryTree;
            Node parentNode;
            while (currentNode != null) {
                node.height++;
                parentNode = currentNode;
                if (currentNode.value == value) {
                    return true;
                } else if (currentNode.value > value) {
                    currentNode = currentNode.left;
                    if (currentNode == null) {
                        node.height++;
                        if (!isSearch) {
                            parentNode.left = node;
                        }
                    }
                } else {
                    currentNode = currentNode.right;
                    if (currentNode == null) {
                        node.height++;
                        if (!isSearch) {
                            parentNode.right = node;
                        }
                    }
                }
            }
        }
        return false;
    }

    static class Node {
        int value;
        Node left;
        Node right;
        int height;

        public String toString() {
            return "" + value;
        }
    }

}
