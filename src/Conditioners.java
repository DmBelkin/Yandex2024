import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Conditioners {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        list.remove(0);
        List<Integer> numbers = Arrays.stream(list.get(0).split("\\s"))
                .map(o -> Integer.parseInt(o))
                .collect(Collectors.toList());
        list.remove(0);
        list.remove(0);
        List<Conditioner> conditioners = new ArrayList<>();
        for (String s : list) {
            String[] data = s.split("\\s");
            int power = Integer.parseInt(data[0]);
            int cost = Integer.parseInt(data[1]);
            Conditioner conditioner = new Conditioner(power, cost);
            conditioners.add(conditioner);
        }
        Comparator<Conditioner> comparator = Comparator.comparing(o -> o.cost);
        Collections.sort(conditioners, comparator);
        Collections.sort(numbers);
        int first = 0;
        int second = 0;
        int totalCost = 0;
        while (first < numbers.size()) {
            int room = numbers.get(first++);
            for (int j = second; j < conditioners.size(); j++) {
                Conditioner conditioner = conditioners.get(j);
                if (conditioner.power >= room) {
                    second = j;
                    totalCost += conditioner.cost;
                    break;
                }
            }
        }
        System.out.println(totalCost);
    }

    public record Conditioner(int power, int cost){}


}
