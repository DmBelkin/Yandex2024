import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class BinarySearchForApproximateValues {

    private static Map<Integer, List<Integer>> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        List<Integer> n1 = Arrays.stream(list.get(1).split("\\s"))
                .map(o -> Integer.parseInt(o))
                .collect(Collectors.toList());
        List<Integer> n2 = Arrays.stream(list.get(2).split("\\s"))
                .map(o -> Integer.parseInt(o))
                .collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n2.size(); i++) {
            builder.append(binarySearch(n1, n2.get(i)) + "\s" + n2.get(i) + "\n" );
        }
        System.out.println(builder.toString().trim());
    }

    private static int binarySearch(List<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = list.get(mid);

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                getResult(0, midVal);
                break;
            }
            int variable = 0;
            if ((key < 0 && midVal > 0) || (key > 0 && midVal < 0)) {
                variable = Math.abs(midVal) + Math.abs(key);
            } else if (key < 0 && midVal < 0 && key < midVal) {
                variable = Math.abs(key) - Math.abs(midVal);
            } else if (key < 0 && midVal < 0) {
                variable = Math.abs(midVal) - Math.abs(key);
            } else if (key > midVal) {
                variable = key - midVal;
            } else {
                variable = midVal - key;
            }
            getResult(variable, midVal);
        }
        for (Map.Entry<Integer, List<Integer>> m : map.entrySet()) {
            List<Integer> l = m.getValue();
            Collections.sort(l);
            map.clear();
            return l.get(0);
        }
        return 0;
    }

    public static void getResult(int subtract, int element) {
        List<Integer> list1 = map.get(subtract);
        if (list1 == null) {
            map.put(subtract, new ArrayList<>() {{
                add(element);
            }});
        } else {
            list1.add(element);
            map.put(subtract, list1);
        }
    }
}
