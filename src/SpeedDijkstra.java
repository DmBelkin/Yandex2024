import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SpeedDijkstra {

    private static long dist[];

    private static Set<Integer> settled;

    private static PriorityQueue<Vertex> pq;

    private static int v;

    private static List<List<Vertex>> graph;

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = list.get(0).split("\\s");
        v = Integer.parseInt(data[0]);
        int k = Integer.parseInt(data[1]);
        if (v == 1) {
            writer.write(Integer.toString(0));
            writer.close();
            return;
        }
        v = Integer.max(k, v);
        List<List<Vertex>> adj = new ArrayList<>();
        for (int i = 0 ; i < Integer.max(k, v); i++) {
             adj.add(new ArrayList<>());
        }
        int i = 1;
        for (; i < list.size() - 1; i++) {
            if (list.get(i).isBlank()) {
                continue;
            }
            String[] d = list.get(i).split("\\s");
            int a  = Integer.parseInt(d[0]);
            int b = Integer.parseInt(d[1]);
            int weight = Integer.parseInt(d[2]);
            Vertex vertex = new Vertex();
            vertex.node = a - 1;
            vertex.cost = weight;
            Vertex vertex1 = new Vertex();
            vertex1.node = b - 1;
            vertex1.cost = weight;
            adj.get(a - 1).add(vertex1);
            adj.get(b - 1).add(vertex);
        }
        String[] ab = list.get(i).split("\\s");
        int a = Integer.parseInt(ab[0]);
        int b = Integer.parseInt(ab[1]);
        graph = adj;
        dist = new long[v];
        settled = new HashSet<>();
        pq = new PriorityQueue<>();
        dijkstra(a - 1);
        if (dist[b - 1] == Long.MAX_VALUE) {
            writer.write(Integer.toString(-1));
        } else {
            writer.write(Long.toString(dist[b - 1]));
        }
        writer.close();
    }

    public static void dijkstra(int src) {
        for (int i = 0; i < v; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        pq.add(new Vertex(src, 0));
        dist[src] = 0;
        while (settled.size() != v) {
            if (pq.isEmpty()) {
                return;
            }
            int u = pq.poll().node;
            if (settled.contains(u)) {
                continue;
            }
            settled.add(u);
            neighbours(u);
        }
    }

    private static void neighbours(int u) {
        long edgeDistance;
        long newDistance;
        for (int i = 0; i < graph.get(u).size(); i++) {
            Vertex v = graph.get(u).get(i);
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;
                if (newDistance < dist[v.node]) {
                    dist[v.node] = newDistance;
                }
                pq.add(new Vertex(v.node, dist[v.node]));
            }
        }
    }
}

class Vertex implements Comparable<Vertex> {

    public int node;

    public long cost;

    public Vertex() {
    }

    public Vertex(int node, long cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "(" +  node + " " + cost + ")";
    }

    @Override
    public int compareTo(Vertex node1) {
        if (node1.cost > this.cost) {
            return -1;
        }
        if (node1.cost < this.cost) {
            return 1;
        }
        return 0;
    }
}
