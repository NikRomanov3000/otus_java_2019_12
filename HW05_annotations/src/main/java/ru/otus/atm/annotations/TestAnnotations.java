package ru.otus.atm.annotations;

import ru.otus.atm.annotations.myAnnotations.After;
import ru.otus.atm.annotations.myAnnotations.Before;
import ru.otus.atm.annotations.myAnnotations.Test;

public class TestAnnotations {

    @Before
    public void testMethodTwo() {
    }

    @Test
    public void testMethodOne1() {}

    @Test
    public void testMethodOne2(){}

    @After
    public void testMethodThree() {
    }
}
