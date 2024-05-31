import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PrefixDoubleArray {


    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] stack = list.get(1).split("\\s");
        List<Integer> l = Arrays.stream(stack).map(o -> Integer.parseInt(o)).collect(Collectors.toList());
        Set<Integer> set = new HashSet<>(l);
        list.remove(0);
        list.remove(0);
        StringBuilder builder = new StringBuilder();
        int[][] prefixes = new int[l.size() + 1][set.size() + 1];
        for (int i = 1; i < l.size() + 1; i++) {
            for (int j = 1; j < set.size() + 1; j++) {
                if (l.get(i - 1) == j) {
                    prefixes[i][j] = prefixes[i - 1][j] + 1;
                } else {
                    prefixes[i][j] = prefixes[i - 1][j];
                }
            }
        }
        for (int[] i : prefixes) {
            System.out.println(Arrays.toString(i));
        }
        for (String s : list) {
            String[] data = s.split("\\s");
            int right = Integer.parseInt(data[1]);
            int left = Integer.parseInt(data[0]);
            if (left == right) {
                builder.append(l.get(left - 1) + "\n");
                continue;
            }
            int middle = right - left + 1;
            boolean strike = false;
            for (int k = 1; k < set.size() + 1; k++) {
                double v = prefixes[right][k] - prefixes[left][k] + 1;
                if (prefixes[left][k] == 0) {
                    v--;
                }
                if (v / middle > 0.5) {
                    System.out.println(left + "___" + right);
                    System.out.println(prefixes[right][k] + " " + prefixes[left][k]);
                    System.out.println();
                    builder.append(k + "\n");
                    strike = true;
                    break;
                }
            }
            if (!strike) {
                builder.append("0" + "\n");
            }
        }
        System.out.println(builder.toString().trim());
    }

}
