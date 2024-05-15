import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class BeautyIsTheBest {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = list.get(0).split("\\s");
        int k = Integer.parseInt(data[1]);
        List<Integer> numbers = Arrays.stream(list.get(1).split("\\s"))
                .map(o -> Integer.parseInt(o))
                .collect(Collectors.toList());
        Map<Integer, List<Integer>> map = new TreeMap<>();
        int first = 0;
        int second = 0;
        while (first < numbers.size()) {
            Set<Integer> set = new HashSet<>();
            while (second <= numbers.size() - 1 && set.size() != k) {
                set.add(numbers.get(second++));
            }
            set.clear();
            int reverseSecond = second - 1;
            while (reverseSecond >= 0 && set.size() != k) {
                set.add(numbers.get(reverseSecond--));
            }
            first = reverseSecond + 1;
            List<Integer> l = new ArrayList<>();
            l.add(first + 1);
            l.add(second);
            map.put(Math.abs(first - second), l);
            first = second;
            second = reverseSecond + 2;
        }
        for (Map.Entry<Integer, List<Integer>> m : map.entrySet()) {
            System.out.println(m.getValue().get(0) + " " +  m.getValue().get(1));
            return;
        }
    }


}
