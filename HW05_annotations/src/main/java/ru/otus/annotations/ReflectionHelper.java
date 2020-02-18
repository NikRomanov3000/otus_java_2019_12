package ru.otus.annotations;

import ru.otus.annotations.myAnnotations.After;
import ru.otus.annotations.myAnnotations.Before;
import ru.otus.annotations.myAnnotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ReflectionHelper {
   private int countOfSuccessTestMethods;
    private int countOfFailTestMethods;


    public ReflectionHelper(){
         countOfSuccessTestMethods=0;
         countOfFailTestMethods=0;
    }

    private  Set<Method> findAnnotationsTestMethodsByName(Class<?> testClass ) throws ClassNotFoundException {
        Set<Method> testMethods = new LinkedHashSet<>();

        for(Method method : testClass.getMethods()){
            if(method.isAnnotationPresent(Test.class)){
                testMethods.add(method);
            }
        }

        return testMethods;
    }

    private Method findAnnotationBeforeMethodsByName(Class<?> testClass) throws ClassNotFoundException {
        int beforeCount=0;
        for (Method method : testClass.getMethods()){
            if(beforeCount<1){
                if(method.isAnnotationPresent(Before.class)){
                    beforeCount++;
                    return method;
                }
            } else throw new RuntimeException("Больше одной аннотации @Before");
        }
        return  null;

    }

    private Method findAnnotationAfterMethodsByName(Class<?> testClass ) throws ClassNotFoundException {
        int afterCount=0;
        for (Method method : testClass.getMethods()){
            if(afterCount<1){
                if(method.isAnnotationPresent(After.class)){
                    afterCount++;
                    return method;
                }
            } else throw new RuntimeException("Больше одной аннотации @After");
        }
      return null;
    }

    public Map<String, Integer> execAnnotationsMethods(String className) throws Exception {
        Class<?> testClass = Class.forName(className);
        Set<Method> testMethod = findAnnotationsTestMethodsByName(testClass);
        Method beforeMethod = findAnnotationBeforeMethodsByName(testClass);
        Method afterMethod = findAnnotationAfterMethodsByName(testClass);
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

            if (method != null && result != false) {
                try {
                    method.invoke(testObj, null);
                    countOfSuccessTestMethods++;
                } catch (Exception e) {
                    countOfFailTestMethods++;
                    System.out.println("Exception in " + method.getName() + " " + e);
                }
            }

            if (afterMethod != null && result != false) {
                try {
                    afterMethod.invoke(testObj, null);
                } catch (Exception e) {
                    result = false;
                    System.out.println("After method failed! " + e);
                    break;
                }
            }
        }


        if (result == false) {
            return null;
        } else {
            Map<String, Integer> statistics = new HashMap<>();
            statistics.put("Success test methods", countOfSuccessTestMethods);
            statistics.put("Fail test methods", countOfFailTestMethods);

            return statistics;
        }
    }
}
