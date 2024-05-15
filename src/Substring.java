import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Substring {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = list.get(0).split("\\s");
        int k = Integer.parseInt(data[1]);
        String input = list.get(1);
        char[] line = input.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : line) {
            set.add(c);
        }
        Map<Integer, Integer> results = new HashMap<>();
        int first = 0;
        int second = 0;
        while (first < line.length) {
            Map<Character, Integer> map = new HashMap<>();
            int n = 0;
            boolean strike = false;
            while (second < line.length && n <= k) {
                char c = line[second++];
                Integer n1 = map.get(c);
                if (n1 == null) {
                    map.put(c, 1);
                } else {
                    map.put(c, n1 + 1);
                    n = n1 + 1;
                    if (n1 + 1 > k) {
                        strike = true;
                    }
                }
            }
            if (map.size() == set.size()) {
                if (!strike) {
                    results.put(first + 1, second - first);
                } else {
                    results.put(first + 1, second - first - 1);
                }
            }
            n = 0;
            while (first < line.length && n <= k) {
                char c = line[first++];
                n = map.get(c);
            }
            second = first;
        }
        String res = "";
        for (Map.Entry<Integer, Integer> m : results.entrySet()) {
            res = m.getValue() + " " + m.getKey();
        }
        System.out.println(res);
    }

}
