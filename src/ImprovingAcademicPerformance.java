import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ImprovingAcademicPerformance {
    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String s = "";
        for (String string : list) {
            s += string + "\s";
        }
        String[] input = s.split("\\s");
        BigInteger a = BigInteger.valueOf(Long.parseLong(input[0]));
        BigInteger b = BigInteger.valueOf(Long.parseLong(input[1]));
        BigInteger c = BigInteger.valueOf(Long.parseLong(input[2]));
        BigInteger l = BigInteger.ZERO;
        BigInteger r = (a.add(b).add(c).multiply(BigInteger.valueOf(5)));
        BigInteger result = BigInteger.ZERO;
        while (l.compareTo(r) < 0) {
            BigInteger mid = l.add(r).divide(BigInteger.TWO);
            if (checkParam(a, b, c, mid)) {
                r = mid;
                result = mid;
            } else {
                l = mid.add(BigInteger.ONE);
            }
        }
        System.out.println(result + " result");
    }

    public static boolean checkParam(BigInteger a, BigInteger b, BigInteger c, BigInteger x) {
        BigInteger count = a.add(b).add(c).add(x);
        BigInteger variable = a.multiply(BigInteger.TWO).add(b.multiply(BigInteger.valueOf(3)))
                .add(c.multiply(BigInteger.valueOf(4))).add(x.multiply(BigInteger.valueOf(5)));
        BigInteger[] div = variable.divideAndRemainder(count);
        double remainder = (double) div[1].longValue() / count.longValue();
        if (remainder < 0.5) {
            remainder = 0;
        }
        return div[0].doubleValue() + remainder >= 3.5;
    }

}
