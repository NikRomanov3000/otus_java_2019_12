import java.lang.annotation.Annotation;
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
        private Map<Method, Boolean> helpMap = new HashMap<>();

        MyInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            Method[] methods = myClass.getClass().getMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Log.class)) {
                    helpMap.put(method, true);
                } else
                    helpMap.put(method, false);
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (helpMap.get(method)) {
                if (args != null)
                    System.out.println("invoking method: " + method.getName() + "with param: " + args[0].toString());
                else
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
