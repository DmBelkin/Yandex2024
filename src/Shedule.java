import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class Shedule {

    public static void main(String[] args) throws IOException {
        String[] dict = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        LocalDate year = LocalDate.of(Integer.parseInt(list.get(1)), 1, 1);
        Map<String, Integer> holyDays = new HashMap<>();
        list.remove(list.size() - 1);
        for (int j = 2; j < list.size(); j++) {
            String[] data = list.get(j).split("\\s");
            int month = 0;
            for (int i = 0; i < dict.length; i++) {
                if (data[1].equals(dict[i])) {
                    month = i + 1;
                }
            }
            LocalDate date = LocalDate.of(year.getYear(), month, Integer.parseInt(data[0]));
            String day = date.getDayOfWeek().toString();
            if (holyDays.get(day) != null) {
                int c = holyDays.get(day).intValue();
                holyDays.put(day, c+1);
            } else {
                holyDays.put(day, 1);
            }
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i < 366; i++) {
            String day = year.getDayOfWeek().toString();

            if (map.get(day) != null) {
                int m = map.get(day).intValue();
                map.put(day, m + 1);
            } else {
                map.put(day, 1);
            }
            if (holyDays.containsKey(day)) {
                map.put(day, map.get(day).intValue() - holyDays.get(day).intValue());
                holyDays.remove(day);
            }
            year = year.plusDays(1);
        }

        List<String[]> sortedList = new ArrayList<>();
        for (Map.Entry<String, Integer> m : map.entrySet()) {

            sortedList.add(new String[]{m.getKey(), m.getValue().toString()});
        }
        Comparator<String[]> comparator = Comparator.comparing(o -> o[1]);
        Collections.sort(sortedList, comparator);
        String better = sortedList.get(sortedList.size() - 1)[0];
        String worse = sortedList.get(0)[0];
        better = better.charAt(0) + better.substring(1).toLowerCase();
        worse = worse.charAt(0) + worse.substring(1).toLowerCase();
        System.out.println(better + " " + worse);
    }

}
