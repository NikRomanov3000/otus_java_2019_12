package ru.otus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class testMain {

    public static void main(String[] args) {

    }

    private void start() throws InterruptedException{
        List<Integer> srcList = new ArrayList();
        List<Integer> myList = new DIYArrayList<Integer>(srcList);

        Integer[] addArray = new Integer[2048];
        Collections.addAll(myList, addArray);
    }
}
