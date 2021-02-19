package ru.job4j.concurrent;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        for(int i = 0; i < total; i++){
            count++;
        }
    }

    public void await() throws InterruptedException {
        if(count == total){
            monitor.notifyAll();
        } else {
            monitor.wait();
        }
    }
}
