package com.corejava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerClient {
    static void main(String[] args) {
        ExecutorService executorService = null;
        Counter counter = new Counter();
        try {
            executorService = Executors.newFixedThreadPool(12);

            Runnable task1 = () -> {
                for (int i = 1; i <= 20000; i++)
                    counter.increment();
            };

            Runnable task2 = () -> {
                for (int i = 1; i <= 80000; i++)
                    counter.increment();
            };

            executorService.submit(task1);
            executorService.submit(task2);

            executorService.awaitTermination(1, TimeUnit.SECONDS);
            System.out.println("Final counter value: " + counter.getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}

class Counter {
    AtomicInteger counter = new AtomicInteger(0);

    public int getValue() {
        return counter.get();
    }

    public int increment() {
        return counter.getAndIncrement();
    }
}
