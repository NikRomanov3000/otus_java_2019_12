package myContext.ru.otus.appcontainer;

import myContext.ru.otus.appcontainer.api.AppComponent;
import myContext.ru.otus.appcontainer.api.AppComponentsContainer;
import myContext.ru.otus.appcontainer.api.AppComponentsContainerConfig;
import myContext.ru.otus.appcontainer.api.AppComponentsContainerException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        fillInMapWithBean(configClass);
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        if (!appComponents.isEmpty()) {
            for (Object obj : appComponents) {
                if (componentClass.isAssignableFrom(obj.getClass())) {
                    return (C) obj;
                }
            }
            throw new RuntimeException("Can't find componentClass: " + componentClass.getName());
        } else {
            throw new RuntimeException("Empty DIY Context");
        }
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        if (appComponentsByName.get(componentName) != null) {
            return (C) appComponentsByName.get(componentName);
        } else {
            throw new RuntimeException("Component wasn't find !");
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private void fillInMapWithBean(Class<?> configClass) {
        List<Method> sortMethods = sortAnnotationMethods(configClass);
        Object configInstance = new Object();
        try {
            configInstance = configClass.getConstructor().newInstance();
        } catch (Exception ex) {
            throw new AppComponentsContainerException(ex);
        }
        for (Method method : sortMethods) {
            Parameter[] parameters = method.getParameters();
            Object[] args = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                try {
                    Object someArg = getAppComponent(parameters[i].getType());
                    args[i] = someArg;
                } catch (Exception ex) {
                    throw new AppComponentsContainerException(ex);
                }
            }
            if (configInstance != null) {
                try {
                    appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), method.invoke(configInstance, args));
                    appComponents.add(method.invoke(configInstance, args));
                } catch (Exception ex) {
                    AppComponentsContainerException exception = new AppComponentsContainerException(ex);
                    exception.printStackTrace();
                }
            }
        }
    }

    private List<Method> sortAnnotationMethods(Class<?> configClass) {
        return Arrays.stream(configClass.getDeclaredMethods()).filter(m -> m.isAnnotationPresent(AppComponent.class))
                .sorted((m1, m2) -> {
                    AppComponent a1 = m1.getAnnotation(AppComponent.class);
                    AppComponent a2 = m2.getAnnotation(AppComponent.class);
                    return Integer.compare(a1.order(), a2.order());
                }).collect(Collectors.toList());
    }
}
