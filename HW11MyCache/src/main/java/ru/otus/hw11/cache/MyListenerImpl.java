package ru.otus.hw11.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.cache.interfaceForHw.MyListener;

public class MyListenerImpl<K, V> implements MyListener<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(MyListenerImpl.class);
    @Override
    public void notify(K key, V value, String action) {
        if(action.equals("put")){
            logger.info("put value in MyCache. key:{}, value:{}",  key, value);
        }
        if(action.equals("remove")){
            logger.info("remove value from MyCache. key:{}",  key);
        }
    }
}
