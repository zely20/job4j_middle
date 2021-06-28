package ru.job4j.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;

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
            Sums sum = new Sums();
            sum.rowSum = sumRow(matrix, i);
            sum.colSum = sumCol(matrix, i);
            sums[i] = sum;
        }
        return sums;
    }

    private static int sumRow(int [][] matrix, int index){
        int result = 0;
        for (int i = 0; i < matrix[index].length; i++){
            result += matrix[index][i];
        }
        return result;
    }

    private static int sumCol(int [][] matrix, int index){
        int result = 0;
        for (int i = 0; i < matrix.length; i++){
            result += matrix[i][index];
        }
        return result;
    }

    public static Sums[] asyncSum(int matrix[][]) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int index = i;
            int sumRow = CompletableFuture.supplyAsync(() ->
                    sumRow(matrix, index)).get();
            int sumCol = CompletableFuture.supplyAsync(() ->
                    sumCol(matrix,index)).get();
            Sums sums1 = new Sums();
            sums1.colSum = sumCol;
            sums1.rowSum = sumRow;
            sums[i] = sums1;
        }
        return sums;
    }
}
