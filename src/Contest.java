import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Contest {


    ///////Отборочные на день стажера
    //+
    public static void main5(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[] c = input.toCharArray();
        double result = 0;
        int worseNum = 0;
        for (int i = 0; i < c.length; i++) {
            worseNum = Integer.max(worseNum, c[i]);
            result += c[i];
        }
        int res = (int)result / c.length;
        double r = Math.abs(res - result / c.length) <= 0.5? res : res + 1;
        r = r < worseNum - 1? worseNum - 1 : r;
        System.out.println((char)r);
    }

    //+
    public static void main1(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        int l = Integer.parseInt(list.get(0));
        String first = list.get(1);
        String second = list.get(2);
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < second.length(); i++) {
            char sym = second.charAt(i);
            Integer s = map.get(sym);
            if (s != null) {
                map.put(sym, s + 1);
            } else {
                map.put(sym, 1);
            }
        }
        int count = 0;
        Map<Character, Integer> map1 = new HashMap<>(map);
        for (int k = 0; k < first.length(); k++) {
            char sym = first.charAt(k);
            Integer s1 = map1.get(sym);
            if (s1 == null) {
                break;
            } else if (s1 > 0) {
                ++count;
                map1.put(sym, s1 - 1);
            } else {
                count = 0;
                map1 = new HashMap<>(map);
            }
            if (count == l) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

    //-
    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = list.get(0).split("\\s");
        int friendsCount = Integer.parseInt(data[0]);
        int seconds = Integer.parseInt(data[1]);
        list.remove(0);
        long[][] arr = new long[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            String[] d = list.get(i).split("\\s");
            int x = Integer.parseInt(d[0]);
            int time = Integer.parseInt(d[1]);
            arr[i] = new long[]{x, time, x + time};
        }
        BigInteger sum = BigInteger.ZERO;
        long coord = 0;
        int result = 0;
        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            long min = Long.MAX_VALUE;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][2] <= min) {
                    min = arr[j][2];
                    m = j;
                }

            }
            coord = Long.max(coord, arr[m][0]);
            sum = sum.add(BigInteger.valueOf(arr[m][1]));
            result++;
            arr[m][2] = (int) Math.pow(10, 10);
            BigInteger s = sum.add(BigInteger.valueOf(coord));
            if (s.compareTo(BigInteger.valueOf(seconds)) == 0) {
                System.out.println(result);
                return;
            } else if (s.compareTo(BigInteger.valueOf(seconds)) > 0) {
                System.out.println(result - 1);
                return;
            }
        }
        System.out.println(result);
    }

    //-
    public static void main8(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] stack = list.get(1).split("\\s");
        List<Integer> l = Arrays.stream(stack).map(o -> Integer.parseInt(o)).collect(Collectors.toList());
        list.remove(0);
        list.remove(0);
        StringBuilder builder = new StringBuilder();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < stack.length; i++) {
            int num = Integer.parseInt(stack[i]);
            List<Integer> n = map.get(num);
            if (n != null) {
                n.add(i);
            } else {
                List<Integer> k = new ArrayList<>();
                k.add(i);
                map.put(num, k);
            }
        }
        for (Map.Entry<Integer, List<Integer>> m : map.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }
        for (String s : list) {
            String[] data = s.split("\\s");
            int start = Integer.parseInt(data[0]);
            int end = Integer.parseInt(data[1]);
            boolean strike = false;
            List<Integer> subStack = new ArrayList<>();
            for (int j = start - 1; j <= end - 1; j++) {
                int d  = l.get(j);
                if (subStack.contains(d)) {
                    continue;
                }
                subStack.add(d);
                List<Integer> indexes = map.get(d);
                int k = Collections.binarySearch(indexes, j);
                int m = end - 1;
                if (m > indexes.get(indexes.size() - 1)) {
                    m = indexes.get(indexes.size() - 1);
                }
                int index = Collections.binarySearch(indexes, m);
                int r = indexes.size() - 1;
                int f = k;
                if (index < 0) {
                    while (indexes.get(r) > m) {
                        r--;
                        index = r;
                        f++;
                        if (indexes.get(f) >= m) {
                            index = f;
                            if (indexes.get(f) > m) {
                                index = f - 1;
                            }
                            break;
                        }
                    }
                }
                int b = index - k + 1;
                if ((double)b / (end - start + 1) > 0.5) {
                    builder.append(d + "\n");
                    strike = true;
                    break;
                }
            }
            if (!strike) {
                builder.append("0" + "\n");
            }
        }
        System.out.println(builder.toString().trim());
    }
}


