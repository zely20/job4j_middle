package ru.job4j.concurrent;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool extends Thread {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(Runtime.getRuntime().availableProcessors());
    private volatile boolean isRunning = true;

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            Runnable runs = null;
            try {
                runs = tasks.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runs.run();
        }
    }

    public void shutdown() {
        isRunning = false;
    }
}

class Main2 {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 7; i < 12; i++) {
                System.out.println(i);
            }
        });

        pool.work(thread1);
        pool.work(thread2);
        pool.run();
        pool.shutdown();
    }
}

