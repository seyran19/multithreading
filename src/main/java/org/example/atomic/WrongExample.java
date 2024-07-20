package org.example.atomic;

public class WrongExample {

     static int i; //переменная не атомарная

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
            i++; // i++ :
                 // int k = i + 1;
                // i = k; --> т.е данная операция не атомарная
        }
    }
}
