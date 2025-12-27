import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Dummy head and tail
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    // Remove a node from the doubly linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Add node right after head (most recently used)
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        remove(node);
        addToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        Node node = new Node(key, value);
        cache.put(key, node);
        addToFront(node);

        if (cache.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    // ----------------- Testing -----------------
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);

        lru.put(1, 10);
        lru.put(2, 20);
        System.out.println(lru.get(1)); // 10

        lru.put(3, 30); // removes key 2
        System.out.println(lru.get(2)); // -1

        lru.put(4, 40); // removes key 1
        System.out.println(lru.get(1)); // -1
        System.out.println(lru.get(3)); // 30
        System.out.println(lru.get(4)); // 40
    }
}
