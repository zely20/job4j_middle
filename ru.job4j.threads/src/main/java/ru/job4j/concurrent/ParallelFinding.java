package ru.job4j.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFinding extends RecursiveTask<Integer> {

    private final int[] array;
    private final Integer value;

    public ParallelFinding(int[] array, Integer value) {
        this.array = array;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (array.length == 10) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return value;
                }
            }
        }

        return -1;
    }

    public static int findIndex(int[] array, int value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFinding(array, value));
    }
}


