import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CowsBullShit {

    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        int playersCount = Integer.parseInt(list.get(0));
        String input = list.get(1);
        String[] data = input.split("\\s");
        List<Integer> arr = new ArrayList<>();
        int max = 0;
        for (String s : data) {
            int n = Integer.parseInt(s);
            arr.add(n);
            max = Integer.max(max, n);
        }
        int winner = arr.indexOf(max);
        List<Integer> results = new ArrayList<>();
        for (int i = winner; i < arr.size() - 1; i++) {
            int s = arr.get(i);
            String num = Integer.toString(s);
            if (num.endsWith("5") && arr.get(i + 1) < s) {
                if (i == winner) {
                    continue;
                }
                results.add(arr.get(i));
            }
        }
        if (results.isEmpty()) {
            System.out.println("0");
            return;
        }
        Collections.sort(results);
        int m = results.get(results.size()-1);
        arr.remove(arr.indexOf(m));
        int place = playersCount;
        for (int i : arr) {
            if (i <= m) {
                place--;
            }
        }
        if (place < playersCount) {
            System.out.println(place);
            return;
        }
        System.out.println("0");
    }
}
