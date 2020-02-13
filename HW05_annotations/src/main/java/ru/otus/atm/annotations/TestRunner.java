package ru.otus.atm.annotations;

public class TestRunner {
    public void runTest() throws Exception {
        ReflectionHelper reflectionHelper = new ReflectionHelper();
        reflectionHelper.execAnnotationsMethods(TestAnnotations.class.getName());
    }
}
