package ru.otus.hw11.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.cache.interfaceForHw.MyCache;
import ru.otus.hw11.cache.interfaceForHw.MyListener;

import java.util.*;

public class MyCacheImpl<K, V> implements MyCache<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(MyCacheImpl.class);

    private Map<K, V> myWeakMap = new WeakHashMap<>();
    private List<MyListener> myListener = new ArrayList<>();
    private final int MAP_LIMIT_SIZE = 100;

    @Override
    public boolean put(K key, V value) {
        if (!checkMaxSize()) {
            myWeakMap.put(key, value);
            LastlistenerNotify(key, value, "put");
            return true;
        } else {
            logger.error("Cache is full");
            return false;
        }

    }

    @Override
    public void remove(K key) {
        LastlistenerNotify(key, null, "remove");
        myWeakMap.remove(key);
    }

    @Override
    public V get(K key) {
        return myWeakMap.get(key);
    }

    @Override
    public boolean addListener(MyListener<K, V> listener) {
        if (!checkListener() && !checkMaxSize()) {
            myListener.add(listener);
            return true;
        } else {
            logger.info("You already have a cache");
            return false;
        }
    }

    @Override
    public void removeListener(MyListener<K, V> listener) {
        myListener.remove(listener);
    }

    @Override
    public int getCacheSize() {
        return myWeakMap.size();
    }

    private boolean checkListener() {
        if (myListener.size() > 0 ) {
            return true;
        } else return false;
    }

    private boolean checkMaxSize() {
        if (myWeakMap.size() == MAP_LIMIT_SIZE) {
            return true;
        } else return false;
    }

    private void LastlistenerNotify(K key, V value, String action) {
        if (checkListener()) {
            myListener.get(myListener.size()-1).notify(key, value, action);
        }
    }

    private void listenerNotifyById(K key, V value, String action, int listenerId) {
        if (checkListener()) {
            myListener.get(listenerId).notify(key, value, action);
        }
    }
}
