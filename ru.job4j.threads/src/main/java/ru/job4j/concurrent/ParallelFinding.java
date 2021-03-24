package ru.job4j.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFinding extends RecursiveTask<Integer> {

    private final int[] array;
    private final Integer value;
    private final Integer from;
    private final Integer to;


    public ParallelFinding(int[] array, Integer value, Integer from, Integer to) {
        this.array = array;
        this.value = value;
        this.from = from;


        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            for (int i = from; i <= to; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static int findIndex(int[] array, int value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFinding(array, value, 0, array.length -1));
    }
}


