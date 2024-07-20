package org.example.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class RightExample {

    static AtomicInteger i = new AtomicInteger(0);  // теперь всегда будем получать 100

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 1000; j++) {
            new MyThread().start();
        }
        Thread.sleep(10);
        System.out.println(i);
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
           i.incrementAndGet();
        }
    }
}
