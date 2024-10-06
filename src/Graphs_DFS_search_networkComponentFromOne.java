import java.io.*;
import java.util.*;

public class Graphs_DFS_search_networkComponentFromOne {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] data = reader.readLine().split("\\s");
        int m = Integer.parseInt(data[1]);
        int n = Integer.parseInt(data[0]);
        Map<Integer, List<Integer>> map = new HashMap<>();
        int entry = 1;
        int maxValue = 0;
        for (int i = 0; i < m; i++) {
            String[] r = reader.readLine().split("\\s");
            if (r.length < 2) {
                continue;
            }
            int a = Integer.parseInt(r[0]);
            int b = Integer.parseInt(r[1]);
            maxValue = Integer.max(maxValue, a);
            maxValue = Integer.max(maxValue, b);
            List<Integer> num = map.get(a);
            List<Integer> num1 = map.get(b);
            if (num == null) {
                map.put(a, new ArrayList<>() {{
                    add(b);
                }});
            } else {
                num.add(b);
                map.put(a, num);
            }
            if (num1 == null) {
                map.put(b, new ArrayList<>() {{
                    add(a);
                }});
            } else {
                num1.add(a);
                map.put(b, num1);
            }
        }
        boolean[] arr = new boolean[maxValue];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = false;
        }
        List<Integer> component = new ArrayList<>();
        if (map.size() > 0) {
            dfs(map, arr, entry, component);
        } else {
            component.add(1);
        }
        Collections.sort(component);
        StringBuilder builder = new StringBuilder();
        builder.append(component.size() + "\n");
        for (int i : component) {
            builder.append(i + "\s");
        }
        writer.write(builder.toString().trim());
        reader.close();
        writer.close();
    }

    public static void dfs(Map<Integer, List<Integer>> graph, boolean[] arr,
                           int n, List<Integer> component) {
        if (!arr[n - 1]) {
            arr[n - 1] = true;
            component.add(n);
            List<Integer> l = graph.get(n);
            if (l != null) {
                for (int i : l) {
                    dfs(graph, arr, i, component);
                }
            }
        }
    }

}
