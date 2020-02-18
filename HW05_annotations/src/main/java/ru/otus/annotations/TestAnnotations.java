package ru.otus.annotations;

import ru.otus.annotations.myAnnotations.After;
import ru.otus.annotations.myAnnotations.Before;
import ru.otus.annotations.myAnnotations.Test;

public class TestAnnotations {
    Adder testAdder;

    @Before
    public void beforeMethod() {
       testAdder = new Adder();
       // throw new RuntimeException();
    }

    @Test
    public void testMethodOne() {
        if(testAdder.add(3,5)!=8)
            throw new RuntimeException("Test method one failed!");
    }

    @Test
    public void testMethodTwo(){
        if(testAdder.add(0,5)!=1) //Специально неправильный тест
            throw new RuntimeException("Test method two failed!");
    }

    @After
    public void afterMethod() {
        System.out.println(testAdder);
    }
}
