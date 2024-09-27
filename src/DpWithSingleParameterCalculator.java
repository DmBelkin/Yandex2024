import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DpWithSingleParameterCalculator {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int[] dp = new int[n + 1];
        for (int i = 2; i < n + 1; i++) {
            int prev = dp[i - 1];
            if (i % 2 == 0) {
                prev = Integer.min(prev, dp[i / 2]);
            }
            if (i % 3 == 0) {
                prev = Integer.min(prev, dp[i / 3]);
            }
            dp[i] = prev + 1;
        }
        StringBuilder builder = new StringBuilder();
        int m = n;
        List<Integer> result = new ArrayList<>();
        while (m > 1) {
            if (dp[m] == dp[m - 1] + 1) {
                result.add(1);
                m--;
                continue;
            }
            if (m % 2 == 0 && dp[m] == dp[m / 2] + 1) {
                result.add(2);
                m /= 2;
                continue;
            }
            result.add(3);
            m /= 3;
        }
        Collections.reverse(result);
        builder.append(dp[dp.length - 1] + "\n");
        builder.append(1 + "\s");
        int r = 1;
        for (int j : result) {
            if (j == 2) {
                r *= 2;
            }
            if (j == 3) {
                r *= 3;
            }
            if (j == 1) {
                r += 1;
            }
            builder.append((r) + "\s");
        }
        writer.write(builder.toString().trim());
        reader.close();
        writer.close();
    }

}
