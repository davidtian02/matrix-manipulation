package com.matrix.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixUtils {
    public static void rotate(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Please provide non-null input");
        }

        for (int i=0; i<(matrix.length>>1); i++) {
            rotate(matrix, i, matrix.length-1-i);
        }
    }

    private static void rotate(int[][] matrix, int left, int right) {
        int temp;
        for (int i=left; i<right; i++) {
            temp = matrix[left][i];
            matrix[left][i] = matrix[right-(i-left)][left];
            matrix[right-(i-left)][left] = matrix[right][right-(i-left)];
            matrix[right][right-(i-left)] = matrix[i][right];
            matrix[i][right] = temp;
        }
    }

    public static int[][] parseMatrix(String input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("Please provide non-null input");
        }

        String condensed = input.replaceAll("\\s+", "");
        if (condensed.length() < 5) {
            throw new IllegalArgumentException("Please provide valid matrix with at least 1 point in matrix");
        }
        String[] arrs = condensed.substring(2, condensed.length()-2).split("],\\[");
        int[][] matrix = new int[arrs.length][];
        for (int i=0; i<arrs.length; i++) {
            int[] arr = Arrays.stream(arrs[i].split(",")).mapToInt(Integer::parseInt).toArray();
            matrix[i] = arr;
        }

        if (!validateSquare(matrix)) {
            throw new IllegalArgumentException("Matrix is not a square. Please provide square matrix");
        }

        return matrix;
    }

    public static boolean validateSquare(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Please provide non-null input");
        }

        return Arrays.stream(matrix).filter(a -> a.length != matrix.length).toArray().length == 0;
    }

    public static String toString(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Please provide non-null input");
        }

        StringBuilder result = new StringBuilder();
        result.append("[");
        List<String> arr = Arrays.stream(matrix).map(Arrays::toString).collect(Collectors.toList());
        String content = String.join(",", arr);
        result.append(content);
        return result.append("]").toString();
    }
}
