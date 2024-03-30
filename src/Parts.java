import java.util.Scanner;

public class Parts {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] data = input.split("\\s");
        int n = Integer.parseInt(data[0]);
        int k = Integer.parseInt(data[1]);
        int m = Integer.parseInt(data[2]);
        if (m > k || k > n) {
            System.out.println(0);
            return;
        }
        int count = 0;
        while (n >= k) {
            int piece = n / k;
            n = n % k;
            int c = k / m;
            count += c * piece;
            n += (k - (m * c)) * piece;
        }
        System.out.println(count);
    }
}
