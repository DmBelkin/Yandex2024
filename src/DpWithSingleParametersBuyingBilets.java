import java.io.*;

public class DpWithSingleParametersBuyingBilets {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int queueSize = Integer.parseInt(reader.readLine());
        int[] dp = new int[queueSize + 3];
        int[][] field = new int[queueSize + 3][3];
        if (queueSize == 1) {
            String[] data = reader.readLine().split("\\s");
            int m = Integer.MAX_VALUE;
            for (String s : data) {
                m = Integer.min(m, Integer.parseInt(s));
            }
            writer.write(Integer.toString(m));
            writer.close();
            reader.close();
            return;
        }
        for (int i = 3; i < queueSize + 3; i++) {
            String[] data = reader.readLine().split("\\s");
            for (int j = 0; j < 3 && j < queueSize; j++) {
                field[i][j] = Integer.parseInt(data[j]);
            }
        }
        for (int i = 3; i < field.length; i++) {
            int a;
            int b;
            if (i == 3) {
                a = Integer.min(field[i][0], field[i][1]);
                dp[i] = a;
            } else if (i == 4) {
                a = Integer.min(dp[i - 1] + field[i][0], dp[i - 2] + field[i - 1][1]);
                dp[i] = a;
            } else {
                a = Integer.min(dp[i - 1] + field[i][0], dp[i - 2] + field[i - 1][1]);
                b = Integer.min(a, dp[i - 3] + field[i - 2][2]);
                dp[i] = b;
            }
        }
        writer.write(Integer.toString(dp[dp.length - 1]));
        reader.close();
        writer.close();
    }


}
