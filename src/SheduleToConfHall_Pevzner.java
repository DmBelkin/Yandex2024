import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SheduleToConfHall_Pevzner {

    public static void main(String[] args) throws IOException {
        File file = new File("input/input.txt");
        List<String> input = Files.readAllLines(Paths.get(file.getPath()));
        int count = Integer.parseInt(input.get(0));
        input.remove(0);
        List<List<Double>> l = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String[] data = input.get(i).split("\\s");
            double a = Double.parseDouble(data[0]);
            double b = Double.parseDouble(data[1]);
            l.add(new ArrayList<>() {{
                add(a);
                add(b);
            }});
        }
        List<List<Double>> combination = new ArrayList<>();
        int i = 0;
        while (i++ < l.size()) {
            List<Double> sub = new ArrayList<>();
            double minStart = Integer.MAX_VALUE;
            for (int j = 0; j < l.size(); j++) {
                List<Double> l2 = l.get(j);
                if (l2.get(1) < minStart) {
                    sub = l2;
                    minStart = l2.get(1); //sort by end of meet
                }
            }
            combination.add(sub);
            for (int k = 0; k < l.size(); k++) {
                List<Double> l1 = l.get(k);
                if (l1.get(0) <= sub.get(1)) {
                    l.remove(k);
                    k--;
                }
            }
            i--;
        }
        System.out.println(combination.size());
    }

}
