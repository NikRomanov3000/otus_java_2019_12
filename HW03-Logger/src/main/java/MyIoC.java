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
        private Map<Method, Boolean> helpMethodsMap = new HashMap<>();

        MyInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            Class<?> myClassInterface = myClass.getClass().getInterfaces()[0];
            Method[] methods = myClass.getClass().getMethods();
           int count = myClass.getClass().getInterfaces()[0].getMethods().length;
            System.out.println(count);
            System.out.println(myClass.getClass().toString());
            System.out.println(myClass.getClass().getInterfaces()[0].toString());

                for (Method method : methods) {
                    if (method.isAnnotationPresent(Log.class))
                        helpMethodsMap.put(method, true);
                    else
                        helpMethodsMap.put(method, false);
                }
        }

        @Override //Map канает внутри invoke
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method);
            // System.out.println(helpMap.get(method));
            //if (helpMethodsMap.get(method)) {
                if (args != null)
                    System.out.println("invoking method: " + method.getName() + "with param: " + args[0].toString());
                else
                    System.out.println("invoking method: " + method.getName());
            //}


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
