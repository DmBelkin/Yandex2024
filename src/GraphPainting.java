import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphPainting {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] data = reader.readLine().split("\\s");
        int students = Integer.parseInt(data[0]);
        int pairs = Integer.parseInt(data[1]);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < pairs; i++) {
            String[] r = reader.readLine().split("\\s");
            int a = Integer.parseInt(r[0]);
            int b = Integer.parseInt(r[1]);
            List<Integer> num = graph.get(a);
            List<Integer> num1 = graph.get(b);
            if (num == null) {
                graph.put(a, new ArrayList<>() {{
                    add(b);
                }});
            } else {
                num.add(b);
                graph.put(a, num);
            }
            if (num1 == null) {
                graph.put(b, new ArrayList<>() {{
                    add(a);
                }});
            } else {
                num1.add(a);
                graph.put(b, num1);
            }
        }
        boolean[] arr = new boolean[students];
        int[] colours = new int[students + 1];
        for (Map.Entry<Integer,List<Integer>> m1 : graph.entrySet()) {
            colours[m1.getKey()] = 1;
            if (arr[m1.getKey() - 1]) {
                continue;
            }
            List<Integer> component = new ArrayList<>();
            String s = dfs(graph, arr, m1.getKey(), component, colours);
            if (s.equals("NO")) {
                writer.write("NO");
                reader.close();
                writer.close();
                return;
            }

        }
        writer.write("YES");
        reader.close();
        writer.close();
    }

    public static String dfs(Map<Integer,List<Integer>> graph, boolean[] arr,
                             int n, List<Integer> component, int[] colours) {
        if (!arr[n - 1]) {
            arr[n - 1] = true;
            component.add(n);
            List<Integer> l = graph.get(n);
            if (l != null) {
                for (int i : l) {
                    if (colours[i] == colours[n]) {
                        return "NO";
                    } else {
                        colours[i] = 3 - colours[n];
                    }
                    dfs(graph, arr, i, component, colours);
                }
            }
        }
        return "YES";
    }

}
