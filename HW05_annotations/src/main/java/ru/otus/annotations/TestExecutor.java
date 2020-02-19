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

private boolean methodInvoker(Method method, Object testObj){
    try {
        method.invoke(testObj, null);
        if(method.isAnnotationPresent(Test.class))
            countOfSuccessTestMethods++;
    } catch (Exception e) {
        System.out.println("Exception in " + method.getName() + " " + e);
        if(method.isAnnotationPresent(Test.class))
            countOfFailTestMethods++;
        return false;
    }
    return true;
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

            if (beforeMethod!= null) {
                result=methodInvoker(beforeMethod, testObj);
            }

            if (method!= null) {
                methodInvoker(method, testObj);
            }

            if (afterMethod != null && result) {
                result=methodInvoker(afterMethod, testObj);
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
