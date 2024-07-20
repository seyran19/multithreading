package org.example.creating;

import java.util.Calendar;
import java.util.concurrent.*;

public class Main {

    // 1 Наследование от класса Thread

    private static Thread thread1 = new Thread("Thread1") {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    };

    // 2. Реализация интерфейса Runnable

    private static Runnable runnable1 = () -> System.out.println(Thread.currentThread().getName());
    private static Thread thread2 = new Thread(runnable1);


    // 3. Реализация интерфейса Callable и использование FutureTask

    private static Callable<Integer> callable = () -> {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            j++;
            Thread.sleep(100);
        }
        return j;
    };

    private static FutureTask<Integer> futureTask = new FutureTask<>(callable);

    private static Thread thread3 = new Thread(futureTask);


    // 4. Использование пула потоков (ExecutorService)

    private static ExecutorService executorService = Executors.newCachedThreadPool(); // -> 1 поток
    private static ExecutorService executorService2 = Executors.newFixedThreadPool(10); // -> 10 потоков
    private static ExecutorService executorService3 = Executors.newCachedThreadPool(); //
                // Cоздает потоки по
                // необходимости, есил поток 60 сек не используется он умирает

    private static Runnable runnable2 = () -> System.out.println("newFixedThreadPool");
    private static Callable<String> callable2 = () -> "newCachedThreadPool";


    // 5. Использование ScheduledExecutorService

    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private static Thread thread5 = new Thread(){
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    };




    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1
        thread1.start();
        //2
        thread2.setName("Thread2");
        thread2.start();
        //3
        thread3.start();
        System.out.println(futureTask.get());

        //4
        Future<String> submit = executorService.submit(callable2);
        executorService.shutdown();
        System.out.println(submit.get());

        executorService2.submit(runnable2);
        executorService2.shutdown();

        //5
        scheduledExecutorService.schedule(thread5, 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();



    }


}
