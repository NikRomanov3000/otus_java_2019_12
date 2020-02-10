package ru.otus.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Set<Class<? extends Annotation>> annotationsForSearch = new HashSet<>();
        annotationsForSearch.add(Before.class);
        annotationsForSearch.add(Test.class);
        annotationsForSearch.add(After.class);

        ExecTest execTest = new ExecTest();
        execTest.execAnnotationsMethods(TestAnnotations.class.getName().toString(),annotationsForSearch );

    }
}
