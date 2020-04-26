package ru.otus.hw14;

public class SequenceOfNumbers {
    private static final int LIMIT = 10;
    private int[] numbers = new int[2 * LIMIT - 1];
    static final Object monitor = new Object();
    private int lastNumber;
    private boolean sequenceChecker = false;

    public SequenceOfNumbers() {
        for (int i = 0; i < LIMIT; i++) {
            numbers[i] = i + 1;
            if (numbers[i] != LIMIT) {
                numbers[numbers.length - (i + 1)] = numbers[i];
            }
        }
        lastNumber = numbers[0];
    }

    public void simpleShowNumbers() {
        System.out.println();
        for (int i : numbers) {
            showNumber(i);
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
                showNumber(i);
                monitor.notify();

                try {
                    while (lastNumber == i) {
                        monitor.wait();

                        if (sequenceChecker == false) {
                            lastNumber++;
                            if (lastNumber == LIMIT + 1) {
                                sequenceChecker = true;
                            }
                        }
                        if (sequenceChecker == true) {
                            lastNumber--;
                        }
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    ex.printStackTrace();
                }
            }
        }
    }
}
