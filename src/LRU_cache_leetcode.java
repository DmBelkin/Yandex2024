import java.util.HashMap;
import java.util.Map;

public class LRU_cache_leetcode {


    private int capacity;

    private Map<Integer, Element> cache = new HashMap<>();

    private Element elements;

    private Element el;

    public LRU_cache_leetcode(int capacity) {
        this.capacity = capacity;
        elements = new Element();
        el = elements;

    }

    public int get(int key) {
        Element element = cache.get(key);
        if (element != null) {
            int c = element.value;
            elements.value = element.value;
            elements.key = element.key;
            Element element1 = elements;
            elements.next = new Element();
            cache.put(key, elements);
            elements = elements.next;
            elements.prev = element1;
            if (element.prev != null) {
                element.prev.next = element.next;
                element.next.prev = element.prev;
            } else {
                el = el.next;
                el.prev = null;
            }
            return c;
        }
        return -1;
    }

    public void put(int key, int value) {
        Element element = cache.get(key);
        if (element != null) {
            elements.value = value;
            elements.key = key;
            Element element1 = elements;
            elements.next = new Element();
            cache.put(key, elements);
            elements = elements.next;
            elements.prev = element1;
            if (element.prev != null) {
                element.prev.next = element.next;
                element.next.prev = element.prev;
            } else {
                el = el.next;
                el.prev = null;
            }
        } else {
            elements.key = key;
            elements.value = value;
            Element element1 = elements;
            elements.next = new Element();
            cache.put(key, elements);
            elements = elements.next;
            elements.prev = element1;
        }
        if (cache.size() > capacity) {
            cache.remove(el.key);
            el = el.next;
            el.prev = null;
        }
    }

    class Element {
        int value;
        int key;
        Element next;
        Element prev;
    }

}
