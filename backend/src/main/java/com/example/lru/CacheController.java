package com.example.lru;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cache")
public class CacheController {

    private LRUCache lruCache;

    @PostMapping("/capacity")
    public void setCapacity(@RequestParam("capacity") int capacity) {
        lruCache = new LRUCache(capacity);
    }

    @PostMapping("/put")
    public void put(
            @RequestParam("key") String key,
            @RequestParam("value") String value) {
        lruCache.put(key, value);
    }

    @GetMapping("/get")
    public String get(@RequestParam("key") String key) {
        return lruCache.get(key);
    }

    // 🔹 This endpoint frontend uses
    @GetMapping("/all")
    public List<Map<String, String>> getCache() {
        List<Map<String, String>> list = new ArrayList<>();

        for (Map.Entry<String, String> e : lruCache.getCache().entrySet()) {
            Map<String, String> obj = new HashMap<>();
            obj.put("key", e.getKey());
            obj.put("value", e.getValue());
            list.add(obj);
        }
        return list; // LRU → MRU
    }
}
