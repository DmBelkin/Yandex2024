import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Trie {

    private TrieNode root;

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = new String[]{};
        int[] result = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            result[i] = trie.search(words[i]);
        }
        System.out.println(Arrays.toString(result));
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                current.children.put(c, new TrieNode());
            }
            current = current.children.get(c);
            current.count++;
        }
        current.terminates = true;
    }

    public int search(String word) {
        int count = 0;
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return count;
            }
            current = current.children.get(c);
            count += current.count;
        }
        return count;
    }


    class TrieNode {
        Map<Character, TrieNode> children;
        boolean terminates;
        int count;

        public TrieNode() {
            this.children = new HashMap<>();
        }
    }

}
