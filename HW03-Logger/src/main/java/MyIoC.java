import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


public class MyIoC {
    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new MyInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(MyIoC.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class MyInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private Map<Method, Boolean> helpMethodsMap = new HashMap<>();


        MyInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            Class<?> myClassInterface = myClass.getClass().getInterfaces()[0];
            Method[] methods = myClass.getClass().getMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Log.class)) {
                    String annotationMethodName;
                    annotationMethodName = method.getName();
                    initHelpMap(myClass, annotationMethodName);
                }
            }
        }

        private void initHelpMap(TestLoggingInterface myClass, String annotationMethodName) {
            Method[] methodsInterface = myClass.getClass().getInterfaces()[0].getMethods();
            for (Method method : methodsInterface) {
                if (method.getName() == annotationMethodName)
                    helpMethodsMap.put(method, true);
                else helpMethodsMap.put(method, false);
            }

        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (helpMethodsMap.get(method)) {
                if (args != null) {
                    System.out.print("invoking method: " + method.getName() + "with param-s: ");
                    for (int i = 0; i < args.length; i++)
                        System.out.println(args[i]);
                } else
                    System.out.println("invoking method: " + method.getName());
            }

            return method.invoke(myClass, args);
        }


        @Override
        public String toString() {
            return "MyInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
