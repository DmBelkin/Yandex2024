import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StyleWear {


    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        List<Integer> ary1 = Arrays.stream(list.get(1).split("\\s")).map(o -> Integer.parseInt(o))
                .collect(Collectors.toList());
        List<Integer> ary2 = Arrays.stream(list.get(3).split("\\s")).map(o -> Integer.parseInt(o))
                .collect(Collectors.toList());
        if (list.get(1).equals(list.get(3))) {
            System.out.println(ary1.get(0) + " " + ary2.get(0));
            return;
        }
        int result = Integer.MAX_VALUE;
        int a = 0;
        int b = 0;
        int first = 0;
        int second = 0;
        for (int i = 0; i < ary1.size() + ary2.size() - 1
                && first < ary1.size()
                && second < ary2.size() ; i++) {
            int n = ary1.get(first);
            int m = ary2.get(second);
            if (Math.abs(n- m) < result) {
                result = Math.abs(n - m);
                a = n;
                b = m;
            }
            if (n == m) {
                System.out.println(n + " " + m);
                return;
            } else if (n < m) {
                first++;
            } else {
                second++;
            }
        }
        System.out.println(a + " " + b);
    }

}
