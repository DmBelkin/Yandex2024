import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphs_DFS_NetworkComponentsCount {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] data = reader.readLine().split("\\s");
        int m = Integer.parseInt(data[1]);
        int n = Integer.parseInt(data[0]);
        StringBuilder builder = new StringBuilder();
        if (n == 1) {
            builder.append(1 + "\n" + 1);
            writer.write(builder.toString());
            writer.close();
            reader.close();
            return;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, null);
        }
        if (m == 0) {
            builder.append(map.size() + "\n");
            for (Map.Entry<Integer, List<Integer>> mp : map.entrySet()) {
                builder.append(1 + "\n");
                builder.append(mp.getKey() + "\n");
            }
            writer.write(builder.toString().trim());
            writer.close();
            reader.close();
            return;
        }
        for (int i = 0; i < m; i++) {
            String[] r = reader.readLine().split("\\s");
            int a = Integer.parseInt(r[0]);
            int b = Integer.parseInt(r[1]);
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
        boolean[] arr = new boolean[map.size()];
        int count = 0;
        for (Map.Entry<Integer,List<Integer>> m1 : map.entrySet()) {
            List<Integer> component = new ArrayList<>();
            if (m1.getValue() == null) {
                count++;
                builder.append(1 + "\n");
                builder.append(m1.getKey() + "\n");
                continue;
            }
            dfs(map, arr, m1.getKey(), component);
            if (!component.isEmpty()) {
                count++;
                builder.append(component.size() + "\n");
                for (int i : component) {
                    builder.append(i + "\s");
                }
                builder.trimToSize();
                builder.append("\n");
            }
        }
        StringBuilder builder1 = new StringBuilder();
        builder1.append(count + "\n");
        builder1.append(builder);
        writer.write(builder1.toString().trim());
        reader.close();
        writer.close();
    }

    public static void dfs(Map<Integer,List<Integer>> graph, boolean[] arr,
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
