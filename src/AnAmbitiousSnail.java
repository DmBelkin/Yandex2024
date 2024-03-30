import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AnAmbitiousSnail {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        list.remove(0);
        List<Berry> positive = new ArrayList<>();
        List<Berry> negative = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            String[] data = s.split("\\s");
            int up = Integer.parseInt(data[0]);
            int down = Integer.parseInt(data[1]);
            int subtract = up - down;
            Berry berry = new Berry(up, down, i + 1, subtract);
            if (subtract < 0) {
                negative.add(berry);
            } else {
                positive.add(berry);
            }

        }
        if (negative.isEmpty()) {
            Comparator<Berry> comparator1 = Comparator.comparing(Berry::down);
            Collections.sort(positive, comparator1);
        } else {
            Comparator<Berry> comparator = Comparator.comparing(Berry::up);
            Collections.sort(negative, comparator);
            Collections.reverse(negative);
            Comparator<Berry> comparator1 = Comparator.comparing(Berry::down);
            Collections.sort(positive, comparator1);
            positive.addAll(negative);
        }
        long high = 0;
        long max = 0;
        StringBuilder builder = new StringBuilder();
        for (Berry berry : positive) {
            high += berry.up;
            max = Long.max(max, high);
            high -= berry.down;
            builder.append(berry.index + "\s");
        }
        System.out.println(max + "\n" + builder.toString().trim());
    }


    public record Berry(int up, int down, int index, int subtract) {

    }

}
