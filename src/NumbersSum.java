import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumbersSum {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = list.get(0).split("\\s");
        List<Integer> l = Arrays.stream(list.get(1).split("\\s"))
                .map(o -> Integer.parseInt(o))
                .collect(Collectors.toList());
        List<Integer> l1 = new ArrayList<>(l);
        int k = Integer.parseInt(data[1]);
        int count = 0;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < l.size(); i++) {
            sum+= l.get(i);
            while (sum > k) {
                sum -= l.get(start++);
            }
            if (sum == k) {
                count++;
                sum -= l.get(start++);
            }
        }
        System.out.println(count);
    }
}
