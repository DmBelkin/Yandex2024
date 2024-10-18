import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BFS_searchShortestPathInUndirectedGraph {

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
        writer.write(Integer.toString(result));
        reader.close();
        writer.close();
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
                    }
                }
            }
        }
        System.out.println(Arrays.toString(distTo));
        if (!hasPath) {
            return -1;
        }
        return distTo[end] - distTo[start];
    }

}
