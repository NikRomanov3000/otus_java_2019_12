package ru.otus.annotations;

import ru.otus.annotations.myAnnotations.After;
import ru.otus.annotations.myAnnotations.Before;
import ru.otus.annotations.myAnnotations.Test;

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
