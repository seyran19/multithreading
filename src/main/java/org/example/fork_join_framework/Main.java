package org.example.fork_join_framework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Main {

    static long startTime = System.currentTimeMillis();

    static long numOfOperations = 50_000_000_000L;
    static int numOfThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(numOfThreads); // вместо 21 секунды заняло 6.
        System.out.println(forkJoinPool.invoke(new MyFork(0, numOfOperations)));


        System.out.println((System.currentTimeMillis() - startTime) / 1000);
    }

    static class MyFork extends RecursiveTask<Long> {

        long from , to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // если операция разбита на достаточное количество частей, выполняем операцию, иначе разбиваем на
            // поменьше

            if((to - from)  <= numOfOperations / numOfThreads){
                long j = 0;

                for (long i = from; i < to; i++) {
                    j += i;
                }
                return j;
            }
            else {
                long middle = (to + from) / 2;
                MyFork firstHalf = new MyFork(from, middle);
                firstHalf.fork();

                MyFork secondHalf = new MyFork(middle + 1, to);
                long secondResult = secondHalf.compute();
                return firstHalf.join() + secondResult;
            }
        }
    }
}


