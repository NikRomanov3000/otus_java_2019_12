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
    int countOfSuccessBeforeMethods;
    int countOfSuccessTestMethods;
    int countOfSuccessAfterMethods;

    int countOfFailBeforeMethods;
    int countOfFailTestMethods;
    int countOfFailAfterMethods;

    public ReflectionHelper(){
        int countOfSuccessBeforeMethods = 0;
        int countOfSuccessTestMethods = 0;
        int countOfSuccessAfterMethods = 0;
        int countOfFailBeforeMethods = 0;
        int countOfFailTestMethods = 0;
        int countOfFailAfterMethods = 0;
    }

    private <T> Map<Class, Set<Method>> findAnnotationsMethodsByName(String className) throws ClassNotFoundException {
        Class<?> testClass = Class.forName(className);
        Set<Method> beforeMethods = new LinkedHashSet<>();
        Set<Method> testMethods = new LinkedHashSet<>();
        Set<Method> afterMethods = new LinkedHashSet<>();

        for(Method method : testClass.getMethods()){
            if(method.isAnnotationPresent(Before.class)){
                    beforeMethods.add(method);
            }
            if(method.isAnnotationPresent(After.class)){
                afterMethods.add(method);
            }
            if(method.isAnnotationPresent(Test.class)){
                testMethods.add(method);
            }
        }

        Map<Class, Set<Method>> mapOfSets = new HashMap<>();
        mapOfSets.put(After.class, afterMethods);
        mapOfSets.put(Before.class, beforeMethods);
        mapOfSets.put(Test.class, afterMethods);

        return mapOfSets;
    }

    public Map<String, Integer> execAnnotationsMethods(String className) throws Exception {
     Class<?> testClass = Class.forName(className);
     Map<Class, Set<Method>> mapOfSets = findAnnotationsMethodsByName(className);

        //Before
        Set<Method> beforeMethods = mapOfSets.get(Before.class);

        for(Method method : beforeMethods){
            try {
                method.invoke(testClass.newInstance(),null);
                countOfSuccessBeforeMethods++;
            } catch (Exception e){
                countOfFailBeforeMethods++;
                throw new Exception("Exception in before method");
            }

        }

        //Test
        Set<Method> testMethods = mapOfSets.get(Test.class);
       for (Method method : testMethods){
           try {
               method.invoke(testClass.newInstance(), null);
               countOfSuccessTestMethods++;
           } catch (Exception e){
               countOfFailTestMethods++;
               throw new Exception("Exception in test method");
           }

       }

        //After
        Set<Method> afterMethods = mapOfSets.get(After.class);
       for(Method method : afterMethods){
           try {
               method.invoke(testClass.newInstance(), null);
               countOfSuccessAfterMethods++;
           } catch (Exception e){
               countOfFailAfterMethods++;
               throw new Exception("Exception in test method");
           }

       }

       Map<String, Integer> statistics = new HashMap<>();
       statistics.put("Success before methods" ,countOfSuccessBeforeMethods);
       statistics.put("Success test methods", countOfSuccessTestMethods);
       statistics.put("Success after methods",countOfSuccessAfterMethods);
       statistics.put("Fail before methods", countOfFailBeforeMethods);
       statistics.put("Fail test methods",countOfFailTestMethods);
       statistics.put("Fail after methods", countOfFailAfterMethods);

       return statistics;
    }
}
