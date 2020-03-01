public class TestLog {
    @Log_annotation
    public void sayHelloUser(String userName) {
        System.out.println("Hello, " + userName);
    }
}
