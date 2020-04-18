package ru.otus.hw13;

public class SequenceOfNumbers {
    private final int LIMIT = 10;
    int [] numbers = new int[2*LIMIT-1];
    static final Object monitor = new Object();

    public SequenceOfNumbers() {
        for(int i=0;i<LIMIT; i++){
            numbers[i] = i+1;
            if(numbers[i]!=LIMIT) {
                numbers[numbers.length - (i + 1)] = numbers[i];
            }
        }
    }

   private void showNumber(){

   }

    public void showNumbers () {
        for(int i : numbers){
            System.out.print(i);
            try{
                Thread.sleep(750);
            } catch (Exception ex){
                System.out.println("Error in method: showNumbers");
            }

        }
    }

    public void synchronizedShowNumbers() {
       // while(true) {
            synchronized (monitor) {
                showNumbers();
                Thread.currentThread().checkAccess();
            }
       // }
    }

    public void twoThreads() throws InterruptedException {
        Thread thread1 = new Thread(this::synchronizedShowNumbers);
        Thread thread2 = new Thread(this::synchronizedShowNumbers);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}
