package org.example.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);
        countDownLatch.await();   // ждем пока счетчик будет равен нулю
        System.out.println("All jobs done!");


    }

}

class Work extends Thread {

    CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done work");
        countDownLatch.countDown();     // делаем какую то работу и уменьшаем счетчик
    }
}