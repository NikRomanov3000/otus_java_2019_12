package ru.otus.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class execTest {

    public void execTestClass(String className) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
    Class<?> testClass =  Class.forName(className);
    Method methods[]=testClass.getMethods();

    for(Method m : methods){
        
    }

    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {
       execTest test = new execTest();
       test.execTestClass(testAnnotations.class.getName().toString());
    }
}
