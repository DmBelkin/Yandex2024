import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TypeOfSequence {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> input = Files.readAllLines(Paths.get(file.getPath()));
        List<Integer> numbers = new ArrayList<>();
        for (String s : input) {
            numbers.add(Integer.parseInt(s));
        }
        int[] counts = new int[3];
        int len = 0;
        double stop = -2 * Math.pow(10, 9);
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i + 1) == stop) {
                len = i;
                break;
            }
            if (numbers.get(i) > numbers.get(i + 1)) {
                counts[0]++;
            } else if (numbers.get(i) < numbers.get(i + 1)) {
                counts[1]++;
            } else if (numbers.get(i).equals(numbers.get(i + 1))) {
                counts[2]++;
            }
        }
        if (counts[0] == len) {
            System.out.println("DESCENDING");
            return;
        }
        if (counts[1] == len) {
            System.out.println("ASCENDING");
            return;
        }
        if (counts[2] == len) {
            System.out.println("CONSTANT");
            return;
        }
        if (counts[0] > 0 && counts[2] > 0 && counts[1] == 0) {
            System.out.println("WEAKLY DESCENDING");
            return;
        }
        if (counts[1] > 0 && counts[2] > 0 && counts[0] == 0) {
            System.out.println("WEAKLY ASCENDING");
            return;
        }
        System.out.println("RANDOM");
    }
}
