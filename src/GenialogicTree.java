import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GenialogicTree {

    private static Node node;

    private static StringBuilder result;

    private static List<Node> nodes = new ArrayList<>();

    private static Map<String, List<String>> map;

    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        int count = Integer.parseInt(list.get(0));
        list.remove(0);
        node = new Node();
        map = new HashMap<>();
        node.name = getMainParent(list);
        node.children = new ArrayList<>();
        nodes.add(node);
        Node tree = node;
        add(node, map);
        Collections.sort(nodes);
        result = new StringBuilder();
        tree.childCount = nodes.size() - 1;
        for (Node node1 : nodes) {
            result.append(node1.name + "\s" + node1.childCount + "\n");
        }
        System.out.println(result.toString().trim());
    }

    public static String getMainParent(List<String> data) {
        List<String> parents = new ArrayList<>();
        List<String> children = new ArrayList<>();
        for (String s : data) {
            String[] d = s.split("\\s");
            parents.add(d[1]);
            children.add(d[0]);
            List<String> child = map.get(d[1]);
            if (child == null) {
                map.put(d[1], new ArrayList<>() {{
                    add(d[0]);
                }});
            } else {
                child.add(d[0]);
                map.put(d[1], child);
            }
        }
        Collections.sort(children);
        for (String parent : parents) {
            int index = Collections.binarySearch(children, parent);
            if (index < 0) {
                return parent;
            }
        }
        return "";
    }

    public static void add(Node node, Map<String, List<String>> map) {
        List<String> data = map.get(node.name);
        if (data != null) {
            for (String s : data) {
                Node child = new Node();
                child.name = s;
                child.parent = node;
                child.children = new ArrayList<>();
                nodes.add(child);
                node.children.add(child);
                add(child, map);
                child.childCount += child.children.size();
                node.childCount += child.childCount;
            }
            map.remove(node.name);
        }
    }

    static class Node implements Comparable<Node> {
        String name;
        Node parent;
        List<Node> children;
        int childCount;

        @Override
        public String toString() {
            return "" + name + " " + children + " " + childCount;
        }

        @Override
        public int compareTo(Node o) {
            return (this.name).compareTo(o.name);
        }
    }
}
