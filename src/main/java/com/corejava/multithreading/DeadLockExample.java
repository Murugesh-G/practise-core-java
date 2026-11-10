package com.corejava.multithreading;

public class DeadLockExample {
    static Object objA = new Object();
    static Object objB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (objA) {
                System.out.println("objA inside");
                synchronized (objB) {
                    System.out.println("objB inside");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (objB) {
                System.out.println("objBB inside");
                synchronized (objA) {
                    System.out.println("objAA inside");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
