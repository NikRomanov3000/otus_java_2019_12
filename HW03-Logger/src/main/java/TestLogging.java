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

    @Log
    @Override
    public void sayHelloAgain() {
        System.out.println("Welcome to the club, buddy");
    }

    @Override
    public String toString() {
        return "TestLogging{}";
    }
}
