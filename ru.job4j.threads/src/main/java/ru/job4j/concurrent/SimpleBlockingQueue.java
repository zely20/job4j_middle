package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) {
        queue.add(value);
        this.notifyAll();
    }

    public synchronized T poll() throws InterruptedException{
        if (queue.size() == 0 || queue.size() == size) {
            this.wait();
        }
        return queue.poll();
    }
}
