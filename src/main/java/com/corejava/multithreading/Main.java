package com.corejava.multithreading;

public class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        thread.start();
    }

}

class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}
