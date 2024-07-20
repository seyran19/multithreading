package org.example.cycleBarier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run());

        // т.е как толькро три спортсмена будут готовы вызовется код из класса Run
        new Sportsman(cyclicBarrier);
        new Sportsman(cyclicBarrier);
        new Sportsman(cyclicBarrier);
    }
}

class Run extends Thread {

    @Override
    public void run() {
        System.out.println("Run is begun");
    }
}

class Sportsman extends Thread {

    CyclicBarrier cyclicBarrier;

    public Sportsman(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        start();
    }

    @Override
    public void run() {
        System.out.println("sportsman is ready");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
