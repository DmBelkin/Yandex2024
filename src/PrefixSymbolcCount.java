import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class PrefixSymbolcCount {

    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        char[] input = Files.readString(Paths.get(file.getPath())).toCharArray();

        Map<Character, Long> map = new TreeMap<>();
        for (int i = 0; i < input.length; i++) {
            long c = (long) (input.length - i) * (i + 1);
            System.out.println(c);
            map.merge(input[i], c, Long::sum);
        }

        for (Map.Entry<Character, Long> m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
    }
}
