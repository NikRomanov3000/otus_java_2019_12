package ru.otus.hw11.cache.interfaceForHw;

public interface MyCache <K, V> {
    boolean put(K key, V value);

    void remove(K key);

    V get(K key);

    boolean addListener(MyListener<K, V> listener);

    void removeListener(MyListener<K, V> listener);

    int getCacheSize();
}
