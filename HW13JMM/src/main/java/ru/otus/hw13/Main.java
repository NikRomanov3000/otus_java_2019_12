package ru.otus.hw13;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SequenceOfNumbers numbers = new SequenceOfNumbers();
        //numbers.showNumbers();
       // numbers.synchronizedShowNumbers();
        numbers.twoThreads();
    }


}
