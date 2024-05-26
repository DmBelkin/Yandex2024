import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class K_ApproximateValuesFromBinarySearch {

    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = list.get(0).split("\\s");
        int k = Integer.parseInt(data[1]);
        String[] nums = list.get(1).split("\\s");
        List<Sight> sights = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int r = Integer.parseInt(nums[i]);
            Sight sight = new Sight(r, i);
            sights.add(sight);
            List<Integer> n = map.get(r);
            if (n == null) {
                int c = i;
                map.put(r, new ArrayList<>() {{
                    add(c);
                }});
            } else {
                n.add(i);
                map.put(r, n);
            }
        }
        Collections.sort(sights);
        int[] result = new int[nums.length];
        Set<Integer> stack = new HashSet<>();
        for (int i = 0; i < sights.size(); i++) {
            int x = Integer.parseInt(nums[i]);
            if (stack.contains(x)) {
                continue;
            }
            List<Sight> l = new ArrayList<>(sights);
            List<Sight> res = binarySearch(l, k + 1, x);
            int sum = 0;
            for (Sight sight : res) {
                sum += Math.abs(x - sight.value);
            }
            List<Integer> indexes = map.get(x);
            for (int j : indexes) {
                result[j] = sum;
            }
            stack.add(x);
        }
        StringBuilder builder = new StringBuilder();
        for (int i : result) {
            builder.append(i + "\s");
        }
        System.out.println(builder.toString().trim());
    }

    public record Sight(int value, int index) implements Comparable<Sight> {
        @Override
        public int compareTo(Sight o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static List<Sight> binarySearch(List<Sight> arr, int k, int x) {
        int n = arr.size();
        if (x <= arr.get(0).value && arr.size() >= k) {
            return arr.subList(0, k);
        } else if (arr.get(n - 1).value <= x) {
            return arr.subList(n - k, n);
        } else {
            int index = Collections.binarySearch(arr, new Sight(x, 0));
            if (index < 0) {
                index = -index - 1;
            }
            int low = Math.max(0, index - k);
            int high = Math.min(arr.size() - 1, index + k - 1);
            while (high - low > k - 1) {
                if ((x - arr.get(low).value) <= (arr.get(high).value - x)) {
                    high--;
                } else if ((x - arr.get(low).value) > (arr.get(high).value - x)) {
                    low++;
                }
            }
            return arr.subList(low, high + 1);
        }
    }

}
