import java.io.*;
import java.util.Arrays;

public class DPWithDoubleParametersAboutTurtle {

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
                    prefixArray[i][j] = Integer.max(prefixArray[i][j - 1], prefixArray[i - 1][j]) + field[i][j];
                }
            }
        }
        for (int[] s : prefixArray) {
            System.out.println(Arrays.toString(s));
        }
        int i = prefixArray.length - 1;
        int j = prefixArray[0].length - 1;
        String way = "";
        // вывести путь, по которому было собрано наибольшее число
        while (i >= 1 && j >= 1) {
            if (prefixArray[i - 1][j] == 0 &&  prefixArray[i][j - 1] == 0) {
                if (j > 1) {
                    way += "R ";
                    j--;
                } else {
                    way += "D ";
                    i--;
                }
            } else if (prefixArray[i - 1][j] >= prefixArray[i][j - 1]) {
                way += "D ";
                i--;
            } else if (prefixArray[i][j - 1] >= prefixArray[i - 1][j]) {
                way += "R ";
                j--;
            }
            if (i == 1 && j == 1) {
                break;
            }
        }
        String result = "";
        for (int k = way.length() - 1; k >= 0; k--) {
            result += way.charAt(k);
        }
        if (m <= 1 && n <= 1) {
            result = "";
        }
        if (prefixArray[prefixArray.length - 1][prefixArray[0].length - 1] == 0) {
            result = "";
            while (n-- > 1) {
                result += "D ";
            }
            while (m-- >  1) {
                result += "R ";
            }
        }
        result = result.trim();
        writer.write(Integer.toString(prefixArray[prefixArray.length - 1][prefixArray[0].length - 1]) + "\n" +
                result);
        reader.close();
        writer.close();
    }

}
