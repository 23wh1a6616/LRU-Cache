import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class LRUCacheServer {

    // ---------- NODE CLASS ----------
    static class Node {
        int key;
        String value;
        Node prev, next;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // ---------- LRU CACHE ----------
    static class LRUCache {
        private final int capacity;
        private final HashMap<Integer, Node> map;
        private final Node head, tail;

        LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();

            head = new Node(0, "");
            tail = new Node(0, "");
            head.next = tail;
            tail.prev = head;
        }

        public String get(int key) {
            if (!map.containsKey(key)) return null;

            Node node = map.get(key);
            remove(node);
            insertAtFront(node);
            return node.value;
        }

        public void put(int key, String value) {
            if (map.containsKey(key)) {
                remove(map.get(key));
            }

            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node newNode = new Node(key, value);
            insertAtFront(newNode);
            map.put(key, newNode);
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertAtFront(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
    }

    // ---------- CACHE INSTANCE ----------
    static LRUCache cache = new LRUCache(3);

    // ---------- HTTP HANDLER ----------
    static void handleRequest(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String response;

        try {
            if (path.startsWith("/put")) {
                // /put/1/Java
                String[] parts = path.split("/");
                int key = Integer.parseInt(parts[2]);
                String value = parts[3];

                cache.put(key, value);
                response = "Inserted successfully";
            }
            else if (path.startsWith("/get")) {
                // /get/1
                String[] parts = path.split("/");
                int key = Integer.parseInt(parts[2]);

                String result = cache.get(key);
                response = (result != null) ? result : "Cache Miss";
            }
            else {
                response = "Invalid endpoint";
            }
        } catch (Exception e) {
            response = "Error: Invalid request format";
        }

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    // ---------- MAIN ----------
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", LRUCacheServer::handleRequest);
        server.setExecutor(null);
        server.start();

        System.out.println("Server started at http://localhost:8080");
    }
}
