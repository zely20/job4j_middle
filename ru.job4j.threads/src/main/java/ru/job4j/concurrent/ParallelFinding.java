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
                return -1;
            }
            int mid = (from + to) / 2;
            // создаем задачи для сортировки частей
            ParallelFinding leftSort = new ParallelFinding(array, value, from, mid);
            ParallelFinding rightSort = new ParallelFinding(array, value, mid + 1, to);
            leftSort.fork();
            rightSort.fork();
            int leftResult = leftSort.join();
            System.out.println(leftResult);
            int rightResult = rightSort.join();
            System.out.println(rightResult);
            return Math.max(leftResult, rightResult);
        }

        public static int findIndex(int[] array, int value){
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            return forkJoinPool.invoke(new ParallelFinding(array, value, 0, array.length - 1));
        }
    }
