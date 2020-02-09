package ru.otus.annotations;

public class testAnnotations {

    @Before
    public void testMethodTwo() {
        System.out.println("Before test");
    }

    @Test
    public void testMethodOne1() {
        System.out.println("Test 1");
    }

    @Test
    public void testMethodOne2() {
        System.out.println("Test 2");
    }

    @After
    public void testMethodThree() {
        System.out.println("After test");
    }
}
