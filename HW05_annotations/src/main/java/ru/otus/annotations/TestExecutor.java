package ru.otus.annotations;

import ru.otus.annotations.myAnnotations.After;
import ru.otus.annotations.myAnnotations.Before;

import static ru.otus.annotations.ReflectionHelper.*;

import java.lang.reflect.Method;
import java.util.*;


public class TestExecutor {
    private int countOfSuccessTestMethods;
    private int countOfFailTestMethods;
    static final String SUCCESS_TEST_NUMBER = "Success test methods";
    static final String FAIL_TEST_NUMBER = "Fail test methods";

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
            } else {
                if (afterMethod != null)
                    result = invokerMethod(afterMethod, testObj);
                break;
            }

            if (afterMethod != null) {
                result = invokerMethod(afterMethod, testObj);
            }
        }

        Map<String, Integer> statistics = new HashMap<>();
        if (!result) {
            statistics.put(SUCCESS_TEST_NUMBER, 0);
            statistics.put(FAIL_TEST_NUMBER, 0);
        }
        statistics.put(SUCCESS_TEST_NUMBER, countOfSuccessTestMethods);
        statistics.put(FAIL_TEST_NUMBER, countOfFailTestMethods);
        return statistics;
    }
}
