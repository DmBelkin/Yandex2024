import java.io.*;
import java.util.*;

public class TopologicSortWithDFS {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] data = reader.readLine().split("\\s");
        int n = Integer.parseInt(data[0]);
        int pairs = Integer.parseInt(data[1]);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n ; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < pairs; i++) {
            String[] r = reader.readLine().split("\\s");
            int a = Integer.parseInt(r[0]);
            int b = Integer.parseInt(r[1]);
            List<Integer> num = graph.get(a);
            if (num == null) {
                graph.put(a, new ArrayList<>() {{
                    add(b);
                }});
            } else {
                num.add(b);
                graph.put(a, num);
            }
        }
        Stack<Integer> stack = new Stack<>();
        boolean[] arr = new boolean[n];
        boolean[] stackCycle = new boolean[n];
        for (Map.Entry<Integer,List<Integer>> m1 : graph.entrySet()) {
            if (arr[m1.getKey() - 1]) {
                continue;
            }
            List<Integer> component = new ArrayList<>();
            dfs(graph, arr, m1.getKey(), stack, component, stackCycle);
            if (component.contains(Integer.MAX_VALUE)) {
                writer.write(Integer.toString(-1));
                reader.close();
                writer.close();
                return;
            }
        }
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            s.append(stack.pop() + "\s");
        }
        writer.write(s.toString().trim());
        reader.close();
        writer.close();
    }

    public static void dfs(Map<Integer,List<Integer>> graph, boolean[] arr,
                           int n, Stack<Integer> stack, List<Integer> component, boolean[] stackCycle) {
        if (stackCycle[n - 1]) {
            component.clear();
            component.add(Integer.MAX_VALUE);
            return;
        }
        stackCycle[n - 1] = true;
        if (!arr[n - 1]) {
            arr[n - 1] = true;
            component.add(n);
            List<Integer> l = graph.get(n);
            if (!l.isEmpty()) {
                for (int i : l) {
                    dfs(graph, arr, i, stack, component, stackCycle);
                }
            }
            stack.add(n);
        }
        stackCycle[n - 1] = false;
    }

}
