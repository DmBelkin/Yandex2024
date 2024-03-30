import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cudes {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] data = list.get(0).split("\\s");
        int anyasCubes  = Integer.parseInt(data[0]);
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            if (i <= anyasCubes) {
                l1.add(Integer.parseInt(list.get(i)));
            } else {
                l2.add(Integer.parseInt(list.get(i)));
            }
        }
        List<Integer> max = l1.size() >= l2.size()? l1 : l2;
        List<Integer> min = l1.size() < l2.size()? l1 : l2;
        int count = 0;
        int count1 = 0;
        int count2 = 0;
        StringBuilder result = new StringBuilder();
        StringBuilder anya = new StringBuilder();
        StringBuilder borya = new StringBuilder();
        Collections.sort(l1);
        Collections.sort(l2);
        if (l1.equals(l2) && anyasCubes > 0) {
            StringBuilder r = new StringBuilder();
            r.append(l1.size() + "\n");
            for (int i : l1) {
                r.append(i + "\s");
            }
            System.out.println(r + "\n" + "0" + "\n\n" + "0");
            return;
        }
        l1.add(0, -1);
        l2.add(0, -2);
        List<Integer> an = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        if (max.contains(-1)) {
            an = max;
            b = min;
        }
        if (min.contains(-1)) {
            an = min;
            b = max;
        }
        for (int i = 0; i < min.size(); i++) {
            int index = Collections.binarySearch(max, min.get(i));
            if (index >= 0) {
                count++;
                result.append(min.get(i) + "\s");
                min.remove(i);
                max.remove(index);
                i--;
            }
        }
        for (int i = 1; i < an.size(); i++) {
            count1++;
            anya.append(an.get(i) + "\s");
        }
        for (int i = 1; i < b.size(); i++) {
            count2++;
            borya.append(b.get(i) + "\s");
        }
        anya.trimToSize();
        borya.trimToSize();
        result.trimToSize();
        String res  = count + "\n" + result + "\n" + count1 + "\n" + anya + "\n"  + count2 + "\n" + borya;
        System.out.println(res);
    }

}
