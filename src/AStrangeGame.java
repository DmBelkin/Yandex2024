import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AStrangeGame {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        int l = Integer.parseInt(list.get(0));
        String first = list.get(1);
        String second = list.get(2);
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < second.length(); i++) {
            char sym = second.charAt(i);
            Integer s = map.get(sym);
            if (s != null) {
                map.put(sym, s + 1);
            } else {
                map.put(sym, 1);
            }
        }
        int count = 0;
        Map<Character, Integer> map1 = new HashMap<>(map);
        for (int k = 0; k < first.length(); k++) {
            char sym = first.charAt(k);
            Integer s1 = map1.get(sym);
            if (s1 == null) {
                break;
            } else if (s1 > 0) {
                ++count;
                map1.put(sym, s1 - 1);
            } else {
                count = 0;
                map1 = new HashMap<>(map);
            }
            if (count == l) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

}
