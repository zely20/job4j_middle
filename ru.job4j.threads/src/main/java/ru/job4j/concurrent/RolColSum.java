package ru.job4j.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = getTask(matrix, i);
        }
        return sums;
    }

    private static Sums getTask(int[][] matrix, int i) {
        int resultRow = 0;
        int resultCol = 0;
        for (int j = 0; j < matrix[i].length; j++) {
            resultRow += matrix[i][j];
            resultCol += matrix[j][i];
        }
        Sums sum = new Sums();
        sum.rowSum = resultRow;
        sum.colSum = resultCol;
        return sum;
    }


    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = getTask(matrix, i);
        }
        return sums;
    }

 /*   private static CompletableFuture<Sums> getTask(int matrix[][], int index) {
        return CompletableFuture.supplyAsync(() -> {
            return getTask(matrix, index);
        });
    }*/
}
