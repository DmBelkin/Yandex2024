import java.math.BigInteger;
import java.util.Scanner;

public class SpaceVillage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = scanner.nextLine().split("\\s");
        BigInteger n = new BigInteger(data[0]);
        BigInteger a = new BigInteger(data[1]);
        BigInteger b = new BigInteger(data[2]);
        BigInteger w = new BigInteger(data[3]);
        BigInteger h = new BigInteger(data[4]);
        BigInteger l = BigInteger.ONE;
        BigInteger r = w.max(h);
        BigInteger result = BigInteger.ZERO;
        while (l.compareTo(r) < 0) {
            BigInteger mid = l.add(r).add(BigInteger.ONE).divide(BigInteger.TWO);
            if (checkParams(w, h, a.add(mid), b.add(mid), n)) {
                l = mid;
                result = mid;
            } else {
                r = mid.subtract(BigInteger.ONE);
            }
        }
        System.out.println("" + (result.divide(BigInteger.TWO)));
    }

    public static boolean checkParams(BigInteger w, BigInteger h, BigInteger a, BigInteger b, BigInteger n) {
        return w.divide(a).multiply(h.divide(b)).compareTo(n) >= 0 ||
                w.divide(b).multiply(h.divide(a)).compareTo(n) >= 0;
    }

}
