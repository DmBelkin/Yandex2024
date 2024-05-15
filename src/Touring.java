import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Touring {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        int count = Integer.parseInt(list.get(0));
        list.remove(0);
        int index = 0;
        int[] peaks = new int[count];
        while (list.get(index).contains("\s")) {
            String[] data = list.get(index).split("\\s");
            int x = Integer.parseInt(data[0]);
            int y = Integer.parseInt(data[1]);
            peaks[index++] = y;
        }
        int first = 0;
        int second = 1;
        int high = 0;
        int last = peaks[0];
        int[] prefixes = new int[count + 1];
        int[] prefixes1 = new int[count + 1];
        for (int i = 0; i < peaks.length - 1; i++) {
            if (peaks[first++] < peaks[second]) {
                high += Math.abs(peaks[second] - last);
                prefixes[second] = high;
                last = peaks[second++];
            } else {
                prefixes[second] = high;
                prefixes1[first - 1] = Math.abs(last - peaks[second]);
                last = peaks[second++];
            }
        }
        if (Arrays.equals(prefixes, prefixes1)) {
            prefixes = new int[count + 1];
            prefixes1 = new int[count + 1];
        }
        int variable = 0;
        for (int i = prefixes1.length - 1; i >= 0; i--) {
            if (prefixes1[i] > 0) {
                variable += prefixes1[i];
            }
            prefixes1[i] = variable;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = index + 1; i < list.size(); i++) {
            String[] data = list.get(i).split("\\s");
            int start = Integer.parseInt(data[0]);
            int end = Integer.parseInt(data[1]);
            if (end > start) {
                int peak = prefixes[start - 1];
                int peak1 = prefixes[end - 1];
                builder.append(Math.abs(peak1 - peak) + "\n");
            } else {
                int peak = prefixes1[start - 1];
                int peak1 = prefixes1[end - 1];
                builder.append(Math.abs(peak1 - peak) + "\n");
            }
        }
        System.out.println(builder.toString().trim());
    }

}
