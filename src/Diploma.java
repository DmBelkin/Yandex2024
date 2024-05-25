import java.math.BigInteger;
import java.util.Scanner;

public class Diploma {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = scanner.nextLine().split("\\s");
        BigInteger w = BigInteger.valueOf(Long.parseLong(data[0]));
        BigInteger h = BigInteger.valueOf(Long.parseLong(data[1]));
        BigInteger n = BigInteger.valueOf(Long.parseLong(data[2]));
        BigInteger max = w.max(h);
        if (n.equals(BigInteger.ONE)) {
            System.out.println(max);
            return;
        }
        BigInteger result = BigInteger.ZERO;
        BigInteger l = BigInteger.ZERO;
        BigInteger r = max.multiply(n);
        while (l.compareTo(r) < 0) {
            //left binarysearch
            BigInteger mid = l.add(r).divide(BigInteger.TWO);
            if (checkParams(mid, w, h, n)) {
                r = mid;
                result = mid;
            } else {
                l = mid.add(BigInteger.ONE);
            }
        }
        System.out.println(result);
    }

    public static boolean checkParams(BigInteger size, BigInteger w , BigInteger h, BigInteger n) {
        return (size.divide(w)).multiply(size.divide(h)).compareTo(n) >= 0;
    }
}
