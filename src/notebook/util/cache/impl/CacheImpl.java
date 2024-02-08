package notebook.util.cache.impl;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl {

    private final Map<Integer, String> cache;

    public CacheImpl(Map<String, String> cache) {
        this.cache = new HashMap<>();
    }

    public void put(Integer key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }
}
