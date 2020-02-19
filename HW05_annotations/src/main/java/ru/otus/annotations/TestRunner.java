package ru.otus.annotations;

import java.util.Map;

public class TestRunner {
    public void runTest() throws Exception {
        TestExecutor reflectionHelper = new TestExecutor();
        Map<String, Integer> results = reflectionHelper.execAnnotationsMethods(TestAnnotations.class.getName());
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
        }
    }
}
