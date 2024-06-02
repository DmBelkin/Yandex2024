import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Squares {
    public static void main(String[] args) throws IOException {

        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        long n = Long.parseLong(list.get(0));
        long m = Long.parseLong(list.get(1));
        long c = Long.parseLong(list.get(2));
        long l = 0;
        long fullSquare = n * m;
        long r = Long.max(n, m);
        long result = 0;
        while (l < r) {
            long mid = (l + r + 1) / 2;
            if (checkParams(n, m, c, mid * 2, fullSquare)) {
                l = mid;
                result = mid;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(result);
    }


    public static boolean checkParams(long n, long m, long count, long r, long fullSquare) {
        return r < (n) && r < (m) && fullSquare - (n - r) * (m - r) <= count;
    }

}
