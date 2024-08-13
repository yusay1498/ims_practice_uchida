package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            for (int i = 0; i < 10; i++) System.out.println("Thread 1");
        });

        executorService.execute(() -> {
            for (int i = 0; i < 10; i++) System.out.println("Thread 2");
        });

        executorService.execute(() -> {
            for (int i = 0; i < 10; i++) System.out.println("Thread 3");
        });

        executorService.execute(() -> {
            for (int i = 0; i < 10; i++) System.out.println("Thread 4");
        });

        executorService.shutdownNow();
    }
}