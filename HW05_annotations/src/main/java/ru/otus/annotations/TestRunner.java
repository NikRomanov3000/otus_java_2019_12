package ru.otus.annotations;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

public class TestRunner {
    public void runTest() throws ClassNotFoundException {
        Set<Class<? extends Annotation>> annotationsForSearch = new LinkedHashSet<>();
        annotationsForSearch.add(Before.class);
        annotationsForSearch.add(Test.class);
        annotationsForSearch.add(After.class);

        ReflectionHelper reflectionHelper = new ReflectionHelper();
        reflectionHelper.execAnnotationsMethods(TestAnnotations.class.getName(),annotationsForSearch );
    }
}
