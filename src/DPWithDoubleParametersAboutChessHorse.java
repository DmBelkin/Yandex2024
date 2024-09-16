import java.io.*;

public class DPWithDoubleParametersAboutChessHorse {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] data = reader.readLine().split("\\s");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int[][] field = new int[n][m];
        field[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i >= 2 && j >= 1 &&  field[i - 2][j - 1] > 0) {
                    field[i][j] += field[i - 2][j - 1];
                }
                if (i >= 1 && j >= 2 && field[i - 1][j - 2] > 0) {
                    field[i][j] += field[i - 1][j - 2];
                }
            }
        }
        int waysCount = field[field.length - 1][field[0].length - 1];
        writer.write(Integer.toString(waysCount));
        reader.close();
        writer.close();
    }

}
