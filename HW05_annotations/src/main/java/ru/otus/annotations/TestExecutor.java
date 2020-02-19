package ru.otus.annotations;

import ru.otus.annotations.myAnnotations.After;
import ru.otus.annotations.myAnnotations.Before;
import static ru.otus.annotations.ReflectionHelper.*;
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
        Set<Method> testMethod = findAnnotationsTestMethodsByName(testClass);
        Method beforeMethod = findAnnotationMethodByName(testClass, Before.class);
        Method afterMethod = findAnnotationMethodByName(testClass, After.class);
        boolean result = true;

        for (Method method : testMethod) {
            Object testObj = testClass.newInstance();

            if (beforeMethod != null) {
                result = invokerMethod(beforeMethod, testObj);
            }

            if (method != null && result) {
                if (invokerMethod(method, testObj))
                    countOfSuccessTestMethods++;
                else countOfFailTestMethods++;
            } else break;

            if (afterMethod != null && result) {
                result = invokerMethod(afterMethod, testObj);
            }
        }

        Map<String, Integer> statistics = new HashMap<>();
        if (!result) {
            statistics.put("Success test methods", 0);
            statistics.put("Fail test methods", 0);
        } else {
            statistics.put("Success test methods", countOfSuccessTestMethods);
            statistics.put("Fail test methods", countOfFailTestMethods);
        }
        return statistics;
    }
}
