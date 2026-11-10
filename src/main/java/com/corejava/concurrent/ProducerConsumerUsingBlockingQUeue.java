package com.corejava.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerUsingBlockingQUeue {
    static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        Thread t1 = new Thread(new AProducer(queue));
        Thread t2 = new Thread(new AConsumer(queue));

        t1.start();
        t2.start();
    }
}

class AConsumer implements Runnable {
    BlockingQueue<Integer> queue;

    public AConsumer(BlockingQueue<Integer> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer i = queue.take();
                System.out.println("Consumer called " + Thread.currentThread().getName() + " consumed " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class AProducer implements Runnable {
    BlockingQueue<Integer> queue;

    public AProducer(BlockingQueue<Integer> q) {
        queue = q;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                queue.put(i);
                System.out.println("Producer called " + Thread.currentThread().getName() + " produced " + i);
                i++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
