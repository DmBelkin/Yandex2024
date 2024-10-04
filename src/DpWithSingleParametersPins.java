import java.io.*;
import java.util.Arrays;

public class DpWithSingleParametersPins {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = Integer.parseInt(reader.readLine());
        int[] field = new int[count + 2];
        String[] data = reader.readLine().split("\\s");
        for (int i = 0; i < count; i++) {
            field[i + 2] = Integer.parseInt(data[i]);
        }
        Arrays.sort(field);
        int[] dp = new int[count + 2];
        for (int i = 2; i <= count + 1; i++) {
            if (i == 2) {
                dp[i] = 0;
            } else if (i == 3 || i == 4) {
                dp[i] = dp[i - 1] + Math.abs(field[i] - field[i - 1]);
            } else {
                int a = Integer.min(dp[i - 2], dp[i - 1]) + Math.abs(field[i] - field[i - 1]);
                dp[i] = a;
            }
        }
        writer.write(Integer.toString(dp[dp.length - 1]));
        reader.close();
        writer.close();
    }
}
