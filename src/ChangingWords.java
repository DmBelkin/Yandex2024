import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ChangingWords {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        List<String> list = Files.readAllLines(Paths.get(file.getPath()));
        String[] dictionary = list.get(0).split("\\s");
        Arrays.sort(dictionary);
        String[] text = list.get(1).split("\\s");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length; i++) {
            boolean isChanged = false;
            for (int j = 0; j < text[i].length(); j++) {
                String s = text[i].substring(0, j);
                int index = Arrays.binarySearch(dictionary, s);
                if (index >= 0) {
                    result.append(dictionary[index] + "\s");
                    isChanged = true;
                    break;
                }
            }
            if (!isChanged) {
                result.append(text[i] + "\s");
            }

        }
        System.out.println(result.toString().trim());
    }

}
