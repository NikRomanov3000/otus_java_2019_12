package ru.otus.annotations;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class TestRunner {
    public void runTest() throws Exception {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Map<String, Integer> results = reflectionHelper.execAnnotationsMethods(TestAnnotations.class.getName());

        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue().toString());
        }
    }
}
