import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Robot {

    /**
     * Slide window
     */

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        int k = Integer.parseInt(list.get(0));
        char[] c = list.get(1).toCharArray();
        int first = 0;
        int second = first + k;
        long result = 0;
        long subResult = 0;
        while (second < c.length) {
            if (c[first] == c[second]) {
                subResult++;
                result += subResult;
            } else {
                subResult = 0;
            }
            first++;
            second++;
        }
        System.out.println(result);
    }

}
