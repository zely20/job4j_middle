package ru.job4j.concurrent;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool extends Thread {
    private final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            threads.add(new Thread(
                    () -> {
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                tasks.poll().run();
                            }
                        } catch (Exception e) {
                            Thread.currentThread().interrupt();
                        }
                    }
            ));
        }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }
}

class Main2 {
    public static void main(String[] args) throws InterruptedException {
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
        pool.shutdown();
    }
}

