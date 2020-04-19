package ru.otus.hw14;

public class SequenceOfNumbers {
    private final int LIMIT = 10;
    int[] numbers = new int[2 * LIMIT - 1];
    static final Object monitor = new Object();

    public SequenceOfNumbers() {
        for (int i = 0; i < LIMIT; i++) {
            numbers[i] = i + 1;
            if (numbers[i] != LIMIT) {
                numbers[numbers.length - (i + 1)] = numbers[i];
            }
        }
    }

    public void simpleShowNumbers(){
        System.out.println();
        for(int i : numbers){
            showNumber(i);
        }
    }

    public void twoThreads(){
        Thread thread1 = new Thread(this::synchronizedShowNumbers);
        Thread thread2 = new Thread(this::synchronizedShowNumbers);

        thread1.start();
        thread2.start();
        //  thread1.join();
        //  thread2.join();
    }

    private void showNumber(int i) {
        System.out.print(i);
    }

    private void synchronizedShowNumbers() {
       for(int i : numbers){
            synchronized (monitor) {
               showNumber(i);
                monitor.notify();
                try {
                    monitor.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
