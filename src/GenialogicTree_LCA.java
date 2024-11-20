import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GenialogicTree_LCA {

    private static SchoolN1.Node node;

    private static SchoolN1.Node firsEl;

    private static StringBuilder result;

    private static List<SchoolN1.Node> nodes = new ArrayList<>();

    private static Map<String, List<String>> map;


    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        int count = Integer.parseInt(list.get(0));
        list.remove(0);
        List<String> sub = list.subList(0, count - 1);
        node = new SchoolN1.Node();
        firsEl = node;
        map = new HashMap<>();
        node.name = getMainParent(sub);
        node.children = new ArrayList<>();
        nodes.add(node);
        SchoolN1.Node tree = node;
        add(node, map);
        Collections.sort(nodes);
        result = new StringBuilder();
        tree.childCount = nodes.size() - 1;
        for (int i = count - 1; i < list.size(); i++) {
            String[] data = list.get(i).split("\\s");
            String firstName = data[0];
            String secondName = data[1];
            int a = Collections.binarySearch(nodes, new SchoolN1.Node(firstName));
            int b = Collections.binarySearch(nodes, new SchoolN1.Node(secondName));
            SchoolN1.Node node1 = nodes.get(a);
            SchoolN1.Node node2 = nodes.get(b);
            result.append(search(node1, node2).name + "\n");
        }
        System.out.println(result.toString().trim());
    }

    public static SchoolN1.Node search(SchoolN1.Node node1, SchoolN1.Node node2) {
        List<SchoolN1.Node> parents1 = new ArrayList<>();
        while (node1 != null) {
            parents1.add(node1);
            if (node1 == node2) {
                return node2;
            }
            node1 = node1.parent;
        }
        Collections.sort(parents1);
        while (node2 != null) {
            int a = Collections.binarySearch(parents1, node2);
            if (a >= 0) {
                return node2;
            }
            node2 = node2.parent;
        }
        return firsEl;
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

    public static SchoolN1.Node add(SchoolN1.Node node, Map<String, List<String>> map) {
        List<String> data = map.get(node.name);
        if (data != null) {
            for (String s : data) {
                SchoolN1.Node child = new SchoolN1.Node();
                child.name = s;
                child.parent = node;
                child.children = new ArrayList<>();
                child.height = node.height + 1;
                nodes.add(child);
                node.children.add(child);
                add(child, map);
                child.childCount += child.children.size();
                node.childCount += child.childCount;
            }
            map.remove(node.name);
        }
        return null;
    }

    static class Node implements Comparable<SchoolN1.Node> {
        String name;
        SchoolN1.Node parent;
        List<SchoolN1.Node> children;
        int childCount;
        int height;

        public Node(){}

        public Node(String  name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "" + name + " " + children + " " + height;
        }

        @Override
        public int compareTo(SchoolN1.Node o) {
            return (this.name).compareTo(o.name);
        }
    }
}
