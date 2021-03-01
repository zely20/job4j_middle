package ru.job4j.concurrent;

public class CountBarrier {
    private final Object monitor = this;
    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        monitor.notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        while (count != total) {
            monitor.wait();
        }
        monitor.notifyAll();
    }
}
