import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ClassicDijkstra {


    private static int v;

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = list.get(0).split("\\s");
        v = Integer.parseInt(data[0]);
        if (v == 1) {
            writer.write(Integer.toString(0));
            writer.close();
            return;
        }
        int a = Integer.parseInt(data[1]);
        int b = Integer.parseInt(data[2]);
        int graph[][] = new int[v][v];
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).isBlank()) {
                continue;
            }
            String[] d = list.get(i).split("\\s");
            int[] ary = new int[v];
            for (int j = 0; j < d.length; j++) {
                ary[j] = Integer.parseInt(d[j]);
            }
            graph[i - 1] = ary;
        }
        ClassicDijkstra t = new ClassicDijkstra();
        int[] dists = t.dijkstra(graph, a - 1);
        int result = dists[b - 1];
        if (result == Integer.MAX_VALUE) {
            result = -1;
        }
        writer.write(Integer.toString(result));
        writer.close();

    }

    public int minDistance(int dist[], boolean[] sptSet) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        for (int i = 0; i < v; i++)
            if (!sptSet[i] && dist[i] <= min) {
                min = dist[i];
                min_index = i;
            }
        return min_index;
    }

    public int[] dijkstra(int graph[][], int src) {
        int dist[] = new int[v];
        boolean sptSet[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;
        for (int i = 0; i < v; i++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int j = 0; j < v; j++) {
                if (!sptSet[j] && graph[u][j] >= 0 &&
                        dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][j] < dist[j]) {
                    dist[j] = dist[u] + graph[u][j];
                }
            }
        }
        return dist;
    }
}
