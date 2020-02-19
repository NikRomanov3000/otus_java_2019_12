package ru.otus.annotations;

import ru.otus.annotations.myAnnotations.Test;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectionHelper {

    public static Set<Method> findAnnotationsTestMethodsByName(Class<?> testClass) throws ClassNotFoundException {
        Set<Method> testMethods = new LinkedHashSet<>();

        for (Method method : testClass.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }
        return testMethods;
    }

    public static Method findAnnotationMethodByName(Class<?> testClass, Class annotation) throws ClassNotFoundException {
        Method neededMethod = null;
        for (Method method : testClass.getMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                if (neededMethod == null) {
                    neededMethod = method;
                } else {
                    throw new RuntimeException("Больше одной аннотации " + annotation.getName());
                }
            }
        }
        return neededMethod;
    }

    public static boolean methodInvoker(Method method, Object testObj) {
        try {
            method.invoke(testObj, null);
        } catch (Exception e) {
            System.out.println("Exception in " + method.getName() + " " + e);
            return false;
        }
        return true;
    }

}
