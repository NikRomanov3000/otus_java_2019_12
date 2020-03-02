public class TestLogging implements TestLoggingInterface {
    @Log
    @Override
    public void sayHelloUser(String userName) {
        System.out.println("Hello, " + userName);
    }

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }

    @Override
    public String toString() {
        return "TestLogging{}";
    }
}
