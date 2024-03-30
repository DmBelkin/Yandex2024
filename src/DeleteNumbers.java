import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DeleteNumbers {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> l = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = l.get(1).split("\\s");
        List<Integer> list = Arrays.stream(data)
                .map(o -> Integer.parseInt(o)).collect(Collectors.toList());
        Collections.sort(list);
        Map<Integer, Integer> map = new TreeMap<>();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            count++;
            if (i == list.size() - 1 || !list.get(i).equals(list.get(i + 1))) {
                map.put(list.get(i), count);
                count = 0;
            }
        }
        int fullS = list.size();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < fullS; i++) {
            int num = list.get(i);
            Integer c1 = map.get(num);
            Integer c2 = map.get(num - 1);
            Integer c3 = map.get(num + 1);
            int first = c1;
            int second = c1;
            if (c2 != null) {
                first = c1 + c2;
            }
            if (c3 != null) {
                second = c1 + c3;
            }
            int min = Integer.min(fullS - first, fullS - second);
            result = Integer.min(result, min);
        }
        System.out.println(result);
    }

}
