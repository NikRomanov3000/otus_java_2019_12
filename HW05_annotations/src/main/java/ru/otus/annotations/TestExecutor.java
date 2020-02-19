package ru.otus.annotations;

import ru.otus.annotations.myAnnotations.After;
import ru.otus.annotations.myAnnotations.Before;
import java.lang.reflect.Method;
import java.util.*;

public class TestExecutor {
    private int countOfSuccessTestMethods;
    private int countOfFailTestMethods;


    public TestExecutor() {
        countOfSuccessTestMethods = 0;
        countOfFailTestMethods = 0;
    }

    public Map<String, Integer> execAnnotationsMethods(String className) throws Exception {
        Class<?> testClass = Class.forName(className);
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Set<Method> testMethod = reflectionHelper.findAnnotationsTestMethodsByName(testClass);
        Method beforeMethod = reflectionHelper.findAnnotationMethodByName(testClass, Before.class);
        Method afterMethod = reflectionHelper.findAnnotationMethodByName(testClass, After.class);
        ;
        boolean result = true;


        for (Method method : testMethod) {
            Object testObj = testClass.newInstance();

            if (beforeMethod != null) {
                result = reflectionHelper.methodInvoker(beforeMethod, testObj);
            }

            if (method != null && result) {
                result = reflectionHelper.methodInvoker(method, testObj);
                if (result)
                    countOfSuccessTestMethods++;
                else countOfFailTestMethods++;
            } else break;

            if (afterMethod != null && result) {
                result = reflectionHelper.methodInvoker(afterMethod, testObj);
            }
        }

        Map<String, Integer> statistics = new HashMap<>();
        if (!result) {
            statistics.put("Success test methods", 0);
            statistics.put("Fail test methods", 0);
            return statistics;
        } else {
            statistics.put("Success test methods", countOfSuccessTestMethods);
            statistics.put("Fail test methods", countOfFailTestMethods);

            return statistics;
        }
    }
}
