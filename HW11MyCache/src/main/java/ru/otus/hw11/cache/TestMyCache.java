package ru.otus.hw11.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw11.cache.interfaceForHw.MyCache;
import ru.otus.hw11.cache.interfaceForHw.MyListener;

public class TestMyCache {
    private static final Logger logger = LoggerFactory.getLogger(TestMyCache.class);

    public static void main(String[] args) {
        new TestMyCache().testMyCache();
    }

    private void testMyCache() {
        MyCache<Integer, Integer> cache = new MyCacheImpl<>();
        MyListener listener = new MyListenerImpl<>();

        cache.addListener(listener);
        cache.put(1, 2007);

        logger.info("getValue:{}", cache.get(1));
        cache.remove(1);
        cache.removeListener(listener);
    }
}
