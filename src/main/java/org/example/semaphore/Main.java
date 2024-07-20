package org.example.semaphore;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore table = new Semaphore(2);

        Person p1 = new Person();
        p1.table = table;
        Person p2 = new Person();
        p2.table = table;
        Person p3 = new Person();
        p3.table = table;
        Person p4 = new Person();
        p4.table = table;
        Person p5 = new Person();
        p5.table = table;
        Person p6 = new Person();
        p6.table = table;
        Person p7 = new Person();
        p7.table = table;

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}

class Person extends Thread {

    Semaphore table;

    @Override
    public void run() {
        System.out.println(this.getName() + " is waiting for table");
        try {
            table.acquire();
            System.out.println(this.getName() + " is eating at the table");
            this.sleep(1000);
            System.out.println(this.getName() + " release table");
            table.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}