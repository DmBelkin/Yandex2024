import java.util.*;

public class ArrangementOfLaptops1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] data = input.split("\\s");
        int[] arr = new int[]{Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                Integer.parseInt(data[2]), Integer.parseInt(data[3])};
        Map<Integer, List<Integer>> map = new TreeMap<>();
        int p = arr[0] * arr[1] + arr[2] * arr[3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < arr.length; k++) {
                    if (i == j || j == k || i == k) {
                        continue;
                    }
                    List<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[j]+ arr[k]);
                    map.put((arr[j] + arr[k]) * arr[i], list);
                }
            }
        }
        for (Map.Entry<Integer, List<Integer>> l : map.entrySet()) {
            if (l.getKey() >= p) {
                System.out.println(l.getValue().get(0) + " " + l.getValue().get(1));
                return;
            }
        }
    }
}
