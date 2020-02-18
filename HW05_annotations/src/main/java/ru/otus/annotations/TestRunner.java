package ru.otus.annotations;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class TestRunner {
    public void runTest() throws Exception {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Map<String, Integer> results = reflectionHelper.execAnnotationsMethods(TestAnnotations.class.getName());
        if(results!=null) {
            for (Map.Entry<String, Integer> entry : results.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().toString());
            }
        } else System.out.println("Тесты не выполнились корректно");
    }
}
