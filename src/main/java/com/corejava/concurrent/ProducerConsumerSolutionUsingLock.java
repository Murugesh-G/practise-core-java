package com.corejava.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerSolutionUsingLock {
    static void main(String[] args) {

    }
}

class BProducer implements Runnable {
    MyBlockingQueue queue;
    int MAX;

    public BProducer(MyBlockingQueue q, int max) {
        queue = q;
        MAX = max;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            System.out.println("Producer trying to put value");
            try {
                queue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BConsumer implements Runnable {
    MyBlockingQueue queue;
    int MAX;

    public BConsumer(MyBlockingQueue q, int max) {
        queue = q;
        MAX = max;
    }

    @Override
    public void run() {
        while (true) {
            int i;
            try {
                i = queue.take();
                Thread.sleep(1000);
                System.out.println("Consumer took the value "+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyBlockingQueue {
    Queue<Integer> queue;
    int MAX;

    ReentrantLock lock = new ReentrantLock(true);
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();

    public MyBlockingQueue() {
        queue = new LinkedList<>();
        MAX = 10;
    }

    void put(Integer i) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == MAX) {
                notFull.await();
            }
            queue.add(i);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    int take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            int i = queue.remove();
            notFull.signalAll();
            return i;
        } finally {
            lock.unlock();
        }

    }
}


