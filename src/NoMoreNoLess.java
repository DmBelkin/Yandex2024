import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NoMoreNoLess {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        list.remove(0);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                continue;
            }
            res.add(Arrays.stream(list.get(i).split("\\s")).map(o -> Integer.parseInt(o))
                    .collect(Collectors.toList()));
        }
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < res.size(); j++) {
            List<Integer> line = res.get(j);
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> r = new ArrayList<>();
            int min = Integer.MAX_VALUE;
            for (int k = 0; k < line.size(); k++) {
                min = Integer.min(min, line.get(k));
                if (r.size() >= min) {
                    result.add(new ArrayList<>(r));
                    r = new ArrayList<>();
                    k--;
                    min = Integer.MAX_VALUE;
                    continue;
                }
                r.add(line.get(k));
            }
            result.add(r);
            builder.append(result.size() + "\n");
            for (List<Integer> l : result) {
                builder.append(l.size() + "\s");
            }
            builder.append("\n");
        }
        System.out.println(builder.toString().trim());
    }

}
