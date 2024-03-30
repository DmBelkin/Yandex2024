import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TwoFromThree {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 > 0) {
                Set<Integer> set = Arrays.stream(list.get(i).split("\\s"))
                        .map(o -> Integer.parseInt(o)).collect(Collectors.toSet());
                List<Integer> l = new ArrayList<>(set);
                for (int j = 0; j < l.size(); j++) {
                    int num = l.get(j);
                    if (map.containsKey(num)) {
                        int val = map.get(num);
                        map.put(num, val + 1);
                    } else {
                        map.put(num, 1);
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if (m.getValue() > 1) {
                builder.append(m.getKey() + "\s");
            }
        }
        System.out.println(builder.toString().trim());
    }
}
