package com.corejava.concurrent;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerUsingWaitNotifyNotifyAll {
    static void main(String[] args) {
        List<Integer> queue = new ArrayList<>();
        int MAX = 10;

        Thread t1 = new Thread(new CProducer(queue, MAX));
        Thread t2 = new Thread(new CConsumer(queue, MAX));

        t1.start();
        t2.start();
    }
}

class CProducer implements Runnable {
    List<Integer> queue;
    int Max;

    public CProducer(List<Integer> q, int m) {
        queue = q;
        Max = m;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Producer is putting value: " + i);
            synchronized (queue) {
                while (queue.size() == 10) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add(i);
                queue.notifyAll();
            }
        }
    }
}

class CConsumer implements Runnable {
    List<Integer> queue;
    int Max;

    public CConsumer(List<Integer> q, int m) {
        queue = q;
        Max = m;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == 0) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = queue.remove(0);
                System.out.println("Consumed value: " + i);
                queue.notifyAll();
            }
        }
    }
}