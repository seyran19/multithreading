package org.example.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Resource r = new Resource();

        Thread1 t1 = new Thread1();
        t1.setR(r);

        Thread1 t2 = new Thread1();
        t2.setR(r);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(r.i);

    }


    static class Thread1 extends Thread {

        Resource r;


        @Override
        public void run() {
            r.changeI();
        }

        public void setR(Resource r) {
            this.r = r;
        }
    }


    static class Resource{
        int i;

        Lock lock = new ReentrantLock();
        // отличается от synchronized тем что он более гибкий
        // можем в одном методе залочить а другом разлочить и пока у нас поток не дойдет до конца второго метода
        // ни один другой поток ничего не сможет сделать

         void changeI(){
            lock.lock();
            int i = this.i;

            Thread.yield();

            i++;
            this.i = i;
            lock.unlock();
        }
    }
}
