import java.util.Scanner;

public class NotEvenResult {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String input1 = scanner.nextLine();
        String[] data = input1.split("\\s");
        long[] d = new long[data.length];
        for (int i = 0; i < data.length; i++) {
            d[i] = Integer.parseInt(data[i]);
        }
        boolean isEven = Math.abs(d[0] % 2) > 0;
        StringBuilder builder = new StringBuilder();
        for (int j = 1; j < d.length; j++) {
            long mod = Math.abs(d[j] % 2);
            if (isEven && mod > 0) {
                builder.append("x");
                isEven = true;
            } else if ((!isEven && mod > 0) || (isEven && mod == 0)) {
                builder.append("+");
                isEven = true;
            } else {
                builder.append("+");
                isEven = false;
            }
        }
        System.out.println(builder);
    }

}
