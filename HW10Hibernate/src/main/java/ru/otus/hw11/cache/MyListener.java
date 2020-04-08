package ru.otus.hw11.cache;

public interface MyListener<K, V> {
    void notify(K key, V value, String action);
}