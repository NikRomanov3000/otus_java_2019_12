package ru.otus.gc.brench;

import java.util.ArrayList;
import java.util.List;

public class Benchmark implements BenchmarkMBean {
    private final int loopCounter;
    private volatile int size = 0; //volatile  Результат операции записи
    // значения в volatile переменную одним потоком, становится виден всем
    // другим потокам, которые используют эту переменную для чтения из нее значения.

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        for(int i=0; i<loopCounter; i++){
            int localSize=size;
            List<Integer> testList = new ArrayList<>();

            for(int j=0; j<localSize; j++) {
                testList.add(9);
            }
            if (i % 2 == 0) {
                testList.remove(i);
            }
            Thread.sleep(10);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size=size;
    }
}
