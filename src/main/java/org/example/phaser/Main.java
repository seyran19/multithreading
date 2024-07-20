package org.example.phaser;

import java.util.concurrent.Phaser;

public class Main {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2); // т.е нужно чтобы разделить работу на фазы
                                     // пока автомойщики не закончать мыть машину мы не можем на мойку загнать следующую
        new Washer(phaser);
        new Washer(phaser);
    }
}

class Washer extends Thread {
    Phaser phaser;

    public Washer(Phaser phaser) {
        this.phaser = phaser;
        start();
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {

            System.out.println(this.getName() + " Washing the car number " + (i + 1));
            phaser.arriveAndAwaitAdvance(); // т.е говорим тут что хотим продождать пока все остальные потоки
                                           // не закончат свою работу
        }

    }
}
