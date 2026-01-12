package com.example.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final LinkedHashMap<String, String> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // accessOrder = true → GET moves entry to MRU
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public synchronized void put(String key, String value) {
        cache.put(key, value);

        if (cache.size() > capacity) {
            // remove LRU
            String lruKey = cache.keySet().iterator().next();
            cache.remove(lruKey);
        }
    }

    public synchronized String get(String key) {
        return cache.getOrDefault(key, "MISS");
    }

    // ✅ THIS is what controller needs
    public synchronized Map<String, String> getCache() {
        return new LinkedHashMap<>(cache); // preserve order
    }

    public synchronized void clear() {
        cache.clear();
    }
}
