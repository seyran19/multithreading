package org.example.synchronize;

public class RightExample {

    public static void main(String[] args) throws InterruptedException {

        ResourceRight resource = new ResourceRight();
        resource.i = 5;

        MyThreadRight myThread1 = new MyThreadRight(resource);
        MyThreadRight myThread2 = new MyThreadRight(resource);

        myThread1.start();
        myThread2.start();

        myThread1.join();
        myThread2.join();

        System.out.println(resource.i); // synchronized - пока один поток не выйдет из класса другой туда не войдет
                                        // работает это с помощью локов, у каждого класса есть невидимый лок
                                        // мы поставили synchronized на метод но блокируется сам объект
    }
}

class MyThreadRight extends Thread {

    ResourceRight resource;

    public MyThreadRight(ResourceRight resource) {
        this.resource = resource;
    }

    @Override
    public void run() {

        resource.changeI();
    }
}

class ResourceRight {

    int i;

    public synchronized void changeI(){

        int i = this.i;
        i++;
        this.i = i;
    }
}
