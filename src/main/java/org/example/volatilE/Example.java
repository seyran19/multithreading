package org.example.volatilE;

public class Example {

    static volatile int i;

    public static void main(String[] args) throws InterruptedException {

        new MyThreadRead().start(); // не будет работать если переменную не поставить volatile
        Thread.sleep(100);   // так как он закешировал значение переменной i и оно всегда равно 0
        new MyThreadWrite().start();

    }

    static class MyThreadWrite extends Thread {

        @Override
        public void run() {
            while (i < 5) {
                System.out.println("Increment i to " + (++i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class MyThreadRead extends Thread {

        @Override
        public void run() {
            int localVar = i;
            while (localVar < 5) {
                if (localVar != i) {
                    System.out.println("new value of i is " + i);
                    localVar = i;

                }
            }

        }
    }

}
