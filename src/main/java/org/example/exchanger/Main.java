package org.example.exchanger;

import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {

        Exchanger<String> ex = new Exchanger<>();
        new Mike(ex);
        new Form(ex);

    }
}

class Mike extends Thread {
    Exchanger<String> ex;

    public Mike(Exchanger<String> ex) {
        this.ex = ex;
        start();
    }

    @Override
    public void run() {
        try {
            ex.exchange("Hi my name is Mike");
            sleep(1000);
            ex.exchange("i am 20 years old");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Form extends Thread {
    Exchanger<String> ex;

    public Form(Exchanger<String> ex) {
        this.ex = ex;
        start();
    }

    @Override
    public void run() {
        try {
            System.out.println(ex.exchange(null));
            System.out.println(ex.exchange(null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
