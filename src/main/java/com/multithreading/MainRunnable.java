package com.multithreading;

public class MainRunnable {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();

       // thread1.start();
        Thread t1=new Thread(thread1);
        t1.start();
        t1.start();

    }

}

class Thread1 implements Runnable {
    public void run() {
        System.out.println("Thread1 is running");
    }
}
