import java.util.Scanner;

public class TheEquationWithTheRoot1 {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if ((a == 0 && c != Math.sqrt(b)) || c < 0) {
            System.out.println("NO SOLUTION");
        } else if (a == 0 && c == Math.sqrt(b)) {
            System.out.println("MANY SOLUTIONS");
        } else {
            double m = Math.pow(c, 2);
            double x = m - b;
            int i = (int)x / a;
            if ( i != x / a) {
                System.out.println("NO SOLUTION");
            } else {
                System.out.println(i);
            }
        }
    }


}
