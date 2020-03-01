import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyIoC {
    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new MyInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(MyIoC.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class MyInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;

        MyInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Log.class))
                System.out.println("invoking method: " + method.getName() + "with param: " + args[0].toString());

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
