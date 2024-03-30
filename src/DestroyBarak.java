import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DestroyBarak {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextInt() + "\n" + scanner.nextInt() + "\n" + scanner.nextInt();
        System.out.println(method1(in));

    }

    public static String method1(String a) {
        String[] arr = a.split("\n");
        int soldiersCount = Integer.parseInt(arr[0]);
        int health = Integer.parseInt(arr[1]);
        int enemiesPerRound = Integer.parseInt(arr[2]);
        if (soldiersCount == health) {
            return "1";
        }
        List<Integer> list = new ArrayList<>();
        for (int j = 2; j <= health + 1; j++) {
            int enemies = 0;
            int count = 0;
            int health1 = health;
            int soldiersCount1 = soldiersCount;
            for (int i = 1; i < Integer.MAX_VALUE; i++) {
                int mySoldiers = soldiersCount1;
                if (i > 1 && health1 > 0) {
                    enemies = enemiesPerRound;
                }
                boolean c = true;
                if (health1 > 0 && i == j && mySoldiers > health1) {
                    int save = health1;
                    health1 -= mySoldiers;
                    mySoldiers -= save;
                    c = false;
                }
                int n = mySoldiers;
                while (enemies > 0 && n > 0) {
                    enemies--;
                    n--;
                    mySoldiers--;
                }
                mySoldiers -= enemies;
                soldiersCount1 -= enemies;
                if (soldiersCount1 <= 0) {
                    break;
                }
                while (health1 > 0 && mySoldiers > 0 && c) {
                    health1--;
                    mySoldiers--;
                }
                count++;
                if (health1 <= 0 && enemies <= 0) {
                    list.add(i);
                    break;
                }
                if (count > health || soldiersCount1 <= 0 && enemies > 0 ||
                        soldiersCount1 <= 0 && health1 > 0) {
                    break;
                }
            }

        }
        if (list.isEmpty()) {
            return "-1";
        }
        Collections.sort(list);
        return "" + list.get(0);
    }

}
