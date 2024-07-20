package org.example.synchronize;

public class WrongExample {

    public static void main(String[] args) throws InterruptedException {

        ResourceRight resource = new ResourceRight();
        resource.i = 5;

        MyThreadRight myThread1 = new MyThreadRight(resource);
        MyThreadRight myThread2 = new MyThreadRight(resource);

        myThread1.start();
        myThread2.start();

        myThread1.join();
        myThread2.join();

        System.out.println(resource.i); // ожидаем получить 7 т.к 2 раза увеличиваем ресурс, но можем получить и 6
    }
}

class MyThread extends Thread {

    ResourceRight resource;

    public MyThread(ResourceRight resource) {
        this.resource = resource;
    }

    @Override
    public void run() {

        resource.changeI();
    }
}

class Resource{

    int i;

    public void changeI(){

        int i = this.i;
        i++;
        this.i = i;
    }
}
