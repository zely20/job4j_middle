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
            int resultRow = 0;
            int resultCol = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                resultRow += matrix[i][j];
                resultCol += matrix[j][i];
            }
            sums[i] = new Sums();
            sums[i].rowSum = resultRow;
            sums[i].colSum = resultCol;
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
            sums[i].rowSum = getTask(matrix, i, true).get().intValue();
            sums[i].colSum = getTask(matrix, i, false).get().intValue();
        }

        return sums;
    }

    private static CompletableFuture<Integer> getTask(int matrix[][], int row, boolean flag) {
        return CompletableFuture.supplyAsync(() -> {
            Integer sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                if(flag == true) {
                    sum += matrix[row][i];
                } else {
                    sum += matrix[i][row];
                }
            }
            return sum;
        });
    }
}
