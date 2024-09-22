import java.io.*;
import java.util.Arrays;

public class CombinaticSequenceWithoutThreeOne {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        long[] dp = new long[n];
        String result = "";
        if (n > 3) {
            dp[0] = 2;
            dp[1] = 4;
            dp[2] = 7;
            for (int i = 3; i < n; i++) {
                dp[i] = dp[i - 2] + dp[i - 1] + dp[i - 3];
            }
            result = Long.toString(dp[dp.length - 1]);
            System.out.println(Arrays.toString(dp));
        }
        if (n == 1) {
            result = Long.toString(2);
        }
        if (n == 2) {
            result = Long.toString(4);
        }
        if (n == 3) {
            result = Long.toString(7);
        }
        writer.write(result);
        reader.close();
        writer.close();
    }

}
