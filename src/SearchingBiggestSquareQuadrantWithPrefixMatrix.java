import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchingBiggestSquareQuadrantWithPrefixMatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = reader.readLine().split("\\s");
        int a = Integer.parseInt(line[0]);
        int b = Integer.parseInt(line[1]);
        int[][] field = new int[a + 1][b + 1];
        int j = 1;
        for (int i = 0; i <= a && j < a + 1; i++) {
            String[] row = reader.readLine().split("\\s");
            int[] r = new int[b + 1];
            int p = 1;
            for (int k = 0; k < row.length && p < b + 1; k++) {
                r[p++] = Integer.parseInt(row[k]);
            }
            field[j++] = r;
        }
        int[][] prefixMatrix = new int[field.length][field[0].length];
        for (int s = 1; s < field.length; s++) {
            for (int k = 1; k < field[s].length; k++) {
                if (field[s][k] == 1) {
                    int min = Math.min(prefixMatrix[s - 1][k], prefixMatrix[s][k - 1]);
                    min = Math.min(min, prefixMatrix[s - 1][k - 1]);
                    prefixMatrix [ s ] [k ] =min + 1;
                }
            }
        }
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 1; i < prefixMatrix.length; i++) {
            for (int k = 1; k < prefixMatrix[i].length; k++) {
                if (prefixMatrix[i][k] > 0) {
                    int q = prefixMatrix[i][k];
                    int i1 = i;
                    int k1 = k;
                    map.put(q, new ArrayList<>() {{
                        add(i1 - q);
                        add(k1 - q);
                    }});
                }
            }
        }
        int biggest = 0;
        int x = 0;
        int y = 0;
        for (Map.Entry<Integer, List<Integer>> m : map.entrySet()) {
            biggest = m.getKey();
            x = m.getValue().get(0) + 1;
            y = m.getValue().get(1) + 1;
        }
        writer.write(biggest + "\n" + x + "\s" + y);
        reader.close();
        writer.close();
    }



}
