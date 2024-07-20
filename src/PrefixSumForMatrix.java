import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PrefixSumForMatrix {

    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> in = Files.readAllLines(Paths.get(file.getPath()));
        String[] params = in.get(0).split("\\s");
        int a = Integer.parseInt(params[0]);
        int b = Integer.parseInt(params[1]);
        int[][] field = new int[a + 1][b + 1];
        in.remove(0);
        int j = 1;
        for (int i = 0; i <= a && j < a + 1; i++) {
            String[] row = in.get(i).split("\\s");
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
                if (s == 1 && k == 1) {
                    prefixMatrix[s][k] = field[s][k];
                } else if (s == 1 && k > 1) {
                    prefixMatrix[s][k] = prefixMatrix[s][k - 1] + field[s][k];
                } else if (s > 1 && k == 1) {
                    prefixMatrix[s][k] = prefixMatrix[s - 1][k] + field[s][k];
                } else {
                    prefixMatrix[s][k] = prefixMatrix[s - 1][k] + prefixMatrix[s][k - 1] -
                            prefixMatrix[s - 1][k - 1] + field[s][k];
                }
            }
        }
        in = in.subList(a, in.size());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < in.size(); i++) {
            String[] data = in.get(i).split("\\s");
            int y1 = Integer.parseInt(data[0]);
            int x1 = Integer.parseInt(data[1]);
            int y2 = Integer.parseInt(data[2]);
            int x2 = Integer.parseInt(data[3]);
            builder.append((prefixMatrix[y2][x2] - prefixMatrix[y1 - 1][x2] -
                    prefixMatrix[y2][x1 - 1] + prefixMatrix[y1 - 1][x1 - 1]) + "\n");
        }
        System.out.println(builder.toString().trim());
    }
}
