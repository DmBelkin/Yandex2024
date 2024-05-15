import java.util.Scanner;

public class FinalAssesment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[] c = input.toCharArray();
        double result = 0;
        int worstNum = 0;
        for (int i = 0; i < c.length; i++) {
            worstNum = Integer.max(worstNum, c[i]);
            result += c[i];
        }
        int res = (int)result / c.length;
        double r = Math.abs(res - result / c.length) <= 0.5? res : res + 1;
        r = r < worstNum - 1? worstNum - 1 : r;
        System.out.println((char)r);
    }

}
