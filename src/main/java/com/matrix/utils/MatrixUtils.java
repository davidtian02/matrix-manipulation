package com.matrix.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixUtils {
    public static void rotate(int[][] matrix) {
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

    public static String toString(int[][] matrix) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        List<String> arr = Arrays.stream(matrix).map(Arrays::toString).collect(Collectors.toList());
        String content = String.join(",", arr);
        result.append(content);
        return result.append("]").toString();
    }

    public static int[][] parseMatrix(String input) throws IllegalArgumentException {
        String condensed = input.replaceAll("\\s+", "");
        if (condensed.length() < 5) {
            throw new IllegalArgumentException("Please provide at least 1 point in matrix");
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
        return Arrays.stream(matrix).filter(a -> a.length != matrix.length).toArray().length == 0;
    }
}
