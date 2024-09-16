import java.io.*;
import java.util.Arrays;

public class NOP_Needleman_Wunsch {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int firstLength = Integer.parseInt(reader.readLine().trim());
        String firstLine = reader.readLine().trim();
        int secondLength = Integer.parseInt(reader.readLine().trim());
        String secondLine = reader.readLine().trim();
        String[] first = firstLine.split("\\s+");
        String[] second = secondLine.split("\\s+");
        String sub = "";
        int[][] field = new int[first.length + 1][second.length + 1];
        for (int i = 1; i <= first.length; i++) {
            for (int j = 1; j <= second.length; j++) {
                if (first[i - 1].equals(second[j - 1])) {
                    field[i][j] = field[i - 1][j - 1] + 1;
                } else {
                    field[i][j] = Integer.max(field[i - 1][j], field[i][j - 1]);
                }
            }
        }
        for (int[] i : field) {
            System.out.println(Arrays.toString(i));
        }
        int i = firstLength;
        int j = secondLength;
        while (i != 0 && j != 0) {
            if (field[i][j] == field[i - 1][j]) {
                i -= 1;
            } else if (field[i][j] == field[i][j - 1]) {
                j -= 1;
            } else {
                sub += first[i - 1] + "\s";
                i -= 1;
                j -= 1;
            }
        }
        String[] s = sub.trim().split("\\s");
        String result = "";
        for (int k = s.length - 1; k >= 0; k--) {
            result += s[k] + "\s";
        }
        writer.write(result.trim());
        reader.close();
        writer.close();
    }


}
