import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS_ShortestWayPerformance {

    private static List<Integer> path;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = Integer.parseInt(reader.readLine());
        int[][] graph = new int[count + 1][count + 1];
        for (int i = 1; i <= count; i++) {
            String[] data = reader.readLine().split("\\s");
            for (int j = 1; j <= count; j++) {
                graph[i][j] = Integer.parseInt(data[j - 1]);
            }
        }
        String[] w = reader.readLine().split("\\s");
        int start = Integer.parseInt(w[0]);
        int end = Integer.parseInt(w[1]);
        int result = 0;
        if (start != end) {
            result = bfs(graph, start, end);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(result + "\n");
        if (result > 0) {
            for (int i : path) {
                builder.append(i + "\s");
            }
        }
        writer.write(builder.toString().trim());
        reader.close();
        writer.close();
    }

    public static List<Integer> pathTo(int v, int[] distTo, int[] edgeTo) {
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.add(0,x);
        }
        path.add(0, x);
        return path;
    }

    private static int bfs(int[][] graph, int start, int end) {
        int[] distTo = new int[graph.length];
        int[] edgeTo = new int[graph.length];
        boolean[] isVisited = new boolean[graph.length];
        Queue<Integer> q = new ArrayDeque<>();
        isVisited[start] = true;
        q.add(start);
        boolean hasPath = false;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i = 1; i < graph.length; i++) {
                if (!isVisited[i] && graph[v][i] == 1) {
                    edgeTo[i] = v;
                    distTo[i] = distTo[v] + 1;
                    isVisited[i] = true;
                    q.add(i);
                    if (i == end) {
                        hasPath = true;
                        break;
                    }
                }
            }
        }
        if (!hasPath) {
            return -1;
        }
        path = new ArrayList<>();
        pathTo(end, distTo, edgeTo);
        return distTo[end] - distTo[start];
    }

}
