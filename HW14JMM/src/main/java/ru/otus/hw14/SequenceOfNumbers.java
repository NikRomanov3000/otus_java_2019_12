package ru.otus.hw14;

public class SequenceOfNumbers {
    private static final int LIMIT = 10;
    private final int[] numbers = new int[2 * LIMIT - 1];
    private static final Object monitor = new Object();
    private String currentThreadName="Thread-1";

    public SequenceOfNumbers() {
        for (int i = 0; i < LIMIT; i++) {
            numbers[i] = i + 1;
            if (numbers[i] != LIMIT) {
                numbers[numbers.length - (i + 1)] = numbers[i];
            }
        }
    }

    public void twoThreads() {
        Thread thread1 = new Thread(this::synchronizedShowNumbers);
        Thread thread2 = new Thread(this::synchronizedShowNumbers);

        thread1.start();
        thread2.start();
    }

    private void showNumber(int i) {
        System.out.println(Thread.currentThread().getName() + ": " + i);
    }

    private void synchronizedShowNumbers() {
        for (int i : numbers) {
            synchronized (monitor) {
                try {
                    while (currentThreadName.equals(Thread.currentThread().getName())) {
                        monitor.wait();
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }
                showNumber(i);
                monitor.notify();
                currentThreadName = Thread.currentThread().getName();
            }
        }
    }
}
