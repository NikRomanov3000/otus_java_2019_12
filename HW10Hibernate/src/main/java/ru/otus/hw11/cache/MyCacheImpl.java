package ru.otus.hw11.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.cache.interfaceForHw.MyCache;
import ru.otus.hw11.cache.interfaceForHw.MyListener;

import java.util.Map;
import java.util.WeakHashMap;

public class MyCacheImpl<K, V> implements MyCache<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(MyCacheImpl.class);

    private Map<K, V> myWeakMap = new WeakHashMap<>();
    private MyListener myListener=null;
    private final int MAP_LIMIT_SIZE = 100;

    @Override
    public void put(K key, V value) {
        if(!checkMaxSize()){
            myWeakMap.put(key, value);
            listenerNotify(key, value, "put");
        } else logger.error("Cache is full");

    }

    @Override
    public void remove(K key) {
        listenerNotify(key, null, "remove");
        myWeakMap.remove(key);
    }

    @Override
    public V get(K key) {
        return myWeakMap.get(key);
    }

    @Override
    public void addListener(MyListener<K, V> listener) {
        if(!checkListener()){
            myListener=listener;
        } else{
            logger.info("You already have a cache");
        }
    }

    @Override
    public void removeListener(MyListener<K, V> listener) {
        myListener=null;
    }

    @Override
    public int getCacheSize(){
        return myWeakMap.size();
    }
    private boolean checkListener(){
        if(myListener!=null){
            return true;
        } else return false;
    }

    private boolean checkMaxSize(){
        if(myWeakMap.size()==MAP_LIMIT_SIZE){
            return true;
        } else return false;
    }

    private void listenerNotify(K key, V value, String action){
        if(checkListener()){
            myListener.notify(key, value, action);
        }
    }
}
