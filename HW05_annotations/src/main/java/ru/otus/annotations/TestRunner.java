package ru.otus.annotations;

import java.lang.reflect.InvocationTargetException;

public class TestRunner {
    public void runTest() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        reflectionHelper.execAnnotationsMethods(TestAnnotations.class.getName());
    }
}
