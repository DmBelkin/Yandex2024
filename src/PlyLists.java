import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PlyLists {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        int count = Integer.parseInt(list.get(0));
        list.remove(0);
        Map<String, Integer> words = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 > 0) {
                String[] data = list.get(i).split("\\s");
                for (String s : data) {
                    if (words.containsKey(s)) {
                        int k = words.get(s);
                        words.put(s, k + 1);
                    } else {
                        words.put(s, 1);
                    }
                }
            }
        }
        int c = 0;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> map : words.entrySet()) {
            if (map.getValue() == count) {
                builder.append(map.getKey() + "\s");
                c++;
            }
        }
        System.out.println(c + "\n" + builder.toString().trim());
    }

}
