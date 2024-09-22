import java.io.*;
import java.util.Arrays;

public class N_bonacci_WaysCountAlghorhythm {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] data = reader.readLine().split("\\s");
        int n = Integer.parseInt(data[0]);
        int k = Integer.parseInt(data[1]);
        int[] dp = new int[n + 2];
        dp[0] = 1;
        for (int i = 2; i <= n + 1; i++) {
            for (int j = i > k ? i - k : 0; j < i; j++) {
                if (i >= 3 && j == 0) {
                    j = 1;
                }
                dp[i] += dp[j];
            }
        }
        String result = Integer.toString(dp[dp.length - 1]);
        if (n == 1 || k == 1) {
            result = Integer.toString(1);
        }
        writer.write(result);
        System.out.println(Arrays.toString(dp));
        reader.close();
        writer.close();
    }
}
