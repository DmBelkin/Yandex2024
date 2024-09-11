import java.io.*;

public class DPWithDoubleParametersAboutMinWay {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] data = reader.readLine().split("\\s");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int[][] field = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String[] numbers = reader.readLine().split("\\s");
            int[] line = new int[m + 1];
            for (int j = 0; j < numbers.length; j++) {
                line[j + 1] = Integer.parseInt(numbers[j]);
            }
            field[i] = line;
        }
        int[][] prefixArray = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (j == 1) {
                    prefixArray[i][j] = prefixArray[i - 1][j] + field[i][j];
                } else if (i == 1) {
                    prefixArray[i][j] = prefixArray[i][j - 1] + field[i][j];
                } else {
                    prefixArray[i][j] = Integer.min(prefixArray[i][j - 1], prefixArray[i - 1][j]) + field[i][j];
                }
            }
        }
        writer.write(Integer.toString(prefixArray[prefixArray.length - 1][prefixArray[0].length - 1]));
        reader.close();
        writer.close();
    }
}
