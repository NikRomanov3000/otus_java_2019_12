package ru.otus.hw11.cache;

import java.util.Map;
import java.util.WeakHashMap;

public class MyCacheImpl<K, V> implements MyCache<K, V> {
    private Map<K, V> myWeakMap = new WeakHashMap<>();
    private final int MAP_LIMIT_SIZE = 100;

    @Override
    public void put(K key, V value) {
        myWeakMap.put(key, value);
    }

    @Override
    public void remove(K key) {
        myWeakMap.remove(key);
    }

    @Override
    public V get(K key) {
        return myWeakMap.get(key);
    }

    @Override
    public void addListener(MyListener<K, V> listener) {

    }

    @Override
    public void removeListener(MyListener<K, V> listener) {

    }
}
