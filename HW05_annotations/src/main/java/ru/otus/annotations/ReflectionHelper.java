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

    private <T> List<Set> findAnnotationsMethodsByName(String className) throws ClassNotFoundException {
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
            if(method.equals(Test.class)){
                testMethods.add(method);
            }
        }

        List<Set> resultSet= new LinkedList<>();
        resultSet.add(beforeMethods);
        resultSet.add(testMethods);
        resultSet.add(afterMethods);

        return resultSet;
    }

    public void execAnnotationsMethods(String className) throws Exception {
        List<Set> setWithAnnotationsMethods=findAnnotationsMethodsByName(className);
       Set<Method> beforeMethods=setWithAnnotationsMethods.get(1);
       Set<Method> testMethods=setWithAnnotationsMethods.get(2);
       Set<Method> afterMethods=setWithAnnotationsMethods.get(3);

        for (Iterator iter = beforeMethods.iterator(); iter.hasNext();) {

            for (Method beforeMethod : beforeMethods) {
                try {beforeMethod.invoke(TestAnnotations.class, null);
                } catch (Exception e){
                    throw new Exception("Exception in before method");
                }

            }
        }
        for (Iterator iter = afterMethods.iterator(); iter.hasNext();) {

            for (Method afterMethod : afterMethods) {
                try {
                    afterMethod.invoke(TestAnnotations.class, null);
                } catch (Exception e){
                    throw new Exception("Exception in after method");
                }
            }
        }
        for (Iterator iter = beforeMethods.iterator(); iter.hasNext();) {

            for (Method testMethod : testMethods) {
                try {
                    testMethod.invoke(TestAnnotations.class, null);
                } catch  (Exception e){
                    throw new Exception("Exception in test method");
                }
            }
        }
    }
}
