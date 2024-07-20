package org.example.waitAndNotify;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    static List<String> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        new Operator().start();
        new Machine().start();
    }

    static class Operator extends Thread {

        Scanner scanner = new Scanner(System.in);

        @Override
        public void run() {

            while (true) {
                synchronized (list) {
                    list.add(scanner.nextLine());
                    list.notifyAll();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Machine extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    while (list.isEmpty()) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    System.out.println(list.remove(0));
                }
            }
        }
    }
}
