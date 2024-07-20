package org.example.deadLock;

public class Example {

    public static void main(String[] args) {

        ResourceA r = new ResourceA();
        ResourceB rb = new ResourceB();

        r.b = rb;
        rb.a = r;

        MyThread thread1 = new MyThread(r);
        MyThread2 thread2 = new MyThread2(rb);


        thread1.start();
        thread2.start();

    }

    public static class MyThread extends Thread {

        ResourceA resourceA;

        public  MyThread(ResourceA resourceA) {
            this.resourceA = resourceA;
        }

        @Override
        public void run() {
            resourceA.getI();

        }
    }

    public static class MyThread2 extends Thread {

        ResourceB resourceB;

        public MyThread2(ResourceB resourceB) {
            this.resourceB = resourceB;
        }

        @Override
        public void run() {
            resourceB.getI();
        }
    }

    public static class ResourceA{
        ResourceB b;


        public synchronized int  returnI(){
            return 1;
        }

        public synchronized void  getI(){
            System.out.println(b.returnI());
        }
    }

    public static class ResourceB{
        ResourceA a;


        public synchronized int returnI(){
            return 1;
        }

        public synchronized void  getI(){
            System.out.println(a.returnI());
        }

    }
}


