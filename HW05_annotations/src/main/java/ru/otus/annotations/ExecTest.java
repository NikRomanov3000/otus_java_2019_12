package ru.otus.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ExecTest {

    public static  <T> Set<Method> findAnnotationsMethodsByName(String className, Class<? extends Annotation>... annotations) throws ClassNotFoundException {
        Class<?> testClass = Class.forName(className);
        Set<Method> methods = new HashSet<>();

        for(Method method : testClass.getMethods()){
            for(Class<? extends Annotation> annotation : annotations){
                if(method.isAnnotationPresent(annotation)){
                    methods.add(method);
                    break;
                }
            }
        }
        return methods;
    }

    public static void execAnnotationsMethods(String className, Set<Class<? extends Annotation>> annotations) throws ClassNotFoundException {

        for (Iterator iter = annotations.iterator(); iter.hasNext();) {

            Set<Method> methodsWithAnnotations = findAnnotationsMethodsByName(className, (Class<? extends Annotation>) iter.next());
            methodsWithAnnotations.forEach(method -> {
                try {
                    method.invoke(TestAnnotations.class, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            });
        }



    }




}
