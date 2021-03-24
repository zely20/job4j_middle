package ru.job4j.concurrent;

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
        Sums[] sums = new Sums[2 * matrix.length];

        for (int i = 0; i < matrix.length; i++){
            int resultRow = 0;
            int resultCol = 0;
            for (int j = 0; j < matrix[i].length ; j++) {
                resultRow += matrix[i][j];
                resultCol += matrix[j][i];
            }
            sums[i] = new Sums();
            sums[i].rowSum = resultRow;
            sums[i].colSum = resultCol;
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        return new Sums[1];
    }
}
