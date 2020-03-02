public class Main {
    public static void main(String[] args) {
        TestLoggingInterface myTest = MyIoC.createMyClass();
        myTest.sayHelloUser("Nikita");
        myTest.sayHello();
    }
}
