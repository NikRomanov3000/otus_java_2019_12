package myContext.ru.otus.appcontainer;

import myContext.ru.otus.App;
import myContext.ru.otus.appcontainer.api.AppComponent;
import myContext.ru.otus.appcontainer.api.AppComponentsContainer;
import myContext.ru.otus.appcontainer.api.AppComponentsContainerConfig;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private Map<Class<?>, Object> helpMap = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        checkConfigClass(configClass);
        FillInMapWithBean(configClass);
        //Код тут
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        if (helpMap.get(componentClass) != null) {
            return (C) helpMap.get(componentClass);
        } else {
            return null;
        }
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        if (appComponentsByName.get(componentName) != null) {
            return (C) appComponentsByName.get(componentName);
        } else {
            return null;
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private void FillInMapWithBean(Class<?> configClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Method> sortMethods = sortAnnotationMethods(configClass);
        Object configInstance = configClass.getConstructor().newInstance();
        for (Method method : sortMethods) {
            Parameter[] parameters = method.getParameters();
            if (parameters.length == 0) {
                appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), method.invoke(configInstance));
                helpMap.put(method.getReturnType(), method.invoke(configInstance));
            } else {
                Object[] args = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    try {
                        Object someArg = getAppComponent(parameters[i].getType());
                        if (someArg != null) {
                            args[i] = someArg;
                        } else {
                            throw new RuntimeException("Cannot find Bean");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), method.invoke(configInstance, args));
                helpMap.put(method.getReturnType(), method.invoke(configInstance, args));
            }
        }
    }


    private List<Method> sortAnnotationMethods(Class<?> configClass) {
        List<Method> sortByOrderList = new ArrayList<>();
        Method[] methods = configClass.getDeclaredMethods();
        for (int annotationCounter = 0; annotationCounter < methods.length; annotationCounter++) {
            for (Method method : methods) {
                Annotation annotation = method.getAnnotation(AppComponent.class);
                if (annotation instanceof AppComponent) {
                    AppComponent appComponent = (AppComponent) annotation;
                    if (appComponent.order() == annotationCounter) {
                        sortByOrderList.add(method);
                    }
                }
            }
        }
        return sortByOrderList;
    }
}
