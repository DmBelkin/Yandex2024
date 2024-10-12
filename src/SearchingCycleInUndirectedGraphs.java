import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SearchingCycleInUndirectedGraphs {

    private static List<Integer> cycle;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            String[] data = reader.readLine().split("\\s");
            int[] ary = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                ary[j] = Integer.parseInt(data[j - 1]);
            }
            graph[i] = ary;
        }
        boolean[] visited = new boolean[n + 1];
        int[] edgeTo = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            List<Integer> cycle = dfs(graph, visited, -1, i, edgeTo);
            if (cycle != null) {
                StringBuilder builder = new StringBuilder();
                builder.append("YES" + "\n");
                builder.append(cycle.size() + "\n");
                for (int k : cycle) {
                    builder.append(k + "\s");
                }
                writer.write(builder.toString().trim());
                reader.close();
                writer.close();
                return;
            }
        }
        writer.write("NO");
        reader.close();
        writer.close();
    }

    public static List<Integer> dfs(int[][] graph, boolean[] arr, int parent, int n, int[] edgeTo) {
        int[] neighbors = graph[n];
        for (int i = 1; i < neighbors.length; i++) {
            if (cycle != null) {
                return cycle;
            }
            if (neighbors[i] == 0) {
                continue;
            }
            if (!arr[i]) {
                arr[i] = true;
                edgeTo[i] = n;
                dfs(graph, arr, n, i, edgeTo);
            } else if (i != parent) {
                cycle = new ArrayList<>();
                for (int j = n; j != i; j = edgeTo[j]) {
                    if (cycle.contains(j)) {
                        cycle = null;
                        return null;
                    }
                    cycle.add(j);
                }
                cycle.add(i);
                cycle.add(n);
                if (cycle.get(cycle.size() - 1).equals(cycle.get(0))) {
                    cycle.remove(cycle.size() - 1);
                }
            }
        }
        return null;
    }

}
