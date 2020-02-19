package ru.otus.annotations;

import ru.otus.annotations.myAnnotations.After;
import ru.otus.annotations.myAnnotations.Before;
import ru.otus.annotations.myAnnotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class TestExecutor {
   private int countOfSuccessTestMethods;
    private int countOfFailTestMethods;


    public TestExecutor(){
         countOfSuccessTestMethods=0;
         countOfFailTestMethods=0;
    }



       public Map<String, Integer> execAnnotationsMethods(String className) throws Exception {
        Class<?> testClass = Class.forName(className);
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        Set<Method> testMethod = reflectionHelper.findAnnotationsTestMethodsByName(testClass);

        Method beforeMethod = reflectionHelper.findAnnotationMethodByName(testClass, Before.class);


        Method afterMethod =  reflectionHelper.findAnnotationMethodByName(testClass, After.class);;
        boolean result = true;


        for (Method method : testMethod) {
            Object testObj = testClass.newInstance();

            if (beforeMethod != null) {
                try {
                    beforeMethod.invoke(testObj, null);
                } catch (Exception e) {
                    result = false;
                    System.out.println("Before method failed! " + e);
                    break;
                }
            }

            if (method != null) {
                try {
                    method.invoke(testObj, null);
                    countOfSuccessTestMethods++;
                } catch (Exception e) {
                    countOfFailTestMethods++;
                    System.out.println("Exception in " + method.getName() + " " + e);
                }
            }

            if (afterMethod != null && result) {
                try {
                    afterMethod.invoke(testObj, null);
                } catch (Exception e) {
                    result = false;
                    System.out.println("After method failed! " + e);
                    break;
                }
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
