package com.matrix.utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MatrixUtilsTest {

    // ================================================================================
    // rotate()
    // ================================================================================

    @Test
    public void rotate_withStandardMatrix_shouldRotateClockwise() {
        int[][] input1 = new int[][]{
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16},
        };
        int[][] input2 = new int[][]{
                {1},
        };
        int[][] input3 = new int[][]{
                {3,3},
                {3,3},
        };

        MatrixUtils.rotate(input1);
        MatrixUtils.rotate(input2);
        MatrixUtils.rotate(input3);

        assertEquals(4, input1.length);
        assertEquals(Arrays.toString(input1[0]), Arrays.toString(new int[]{15,13,2,5}));
        assertEquals(Arrays.toString(input1[1]), Arrays.toString(new int[]{14,3,4,1}));
        assertEquals(Arrays.toString(input1[2]), Arrays.toString(new int[]{12,6,8,9}));
        assertEquals(Arrays.toString(input1[3]), Arrays.toString(new int[]{16,7,10,11}));
        assertEquals(1, input2.length);
        assertEquals(1, input2[0].length);
        assertEquals(1, input2[0][0]);
        assertEquals(2, input3.length);
        assertEquals(Arrays.toString(input3[0]), Arrays.toString(new int[]{3,3}));
        assertEquals(Arrays.toString(input3[1]), Arrays.toString(new int[]{3,3}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rotate_withInvalidInput_shouldThrowException() {
        int[][] input = null;

        MatrixUtils.rotate(input);

        // exception caught by jUnit
    }

    // ================================================================================
    // parseMatrix()
    // ================================================================================

    @Test
    public void parseMatrix_withOnePointMatrixAndWhitespaces_shouldReturnMatrix() {
        String input = "  [ [1  ]] ";

        int[][] result = MatrixUtils.parseMatrix(input);

        assertEquals(1, result.length);
        assertEquals(1, result[0].length);
        assertEquals(1, result[0][0]);
    }

    @Test
    public void parseMatrix_withSquareMatrix_shouldParseAndReturn() {
        String input = "[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]";

        int[][] result = MatrixUtils.parseMatrix(input);

        assertEquals(4, result.length);
        assertEquals(4, result[0].length);
        assertEquals(Arrays.toString(new int[]{5,1,9,11}), Arrays.toString(result[0]));
        assertEquals(Arrays.toString(new int[]{2,4,8,10}), Arrays.toString(result[1]));
        assertEquals(Arrays.toString(new int[]{13,3,6,7}), Arrays.toString(result[2]));
        assertEquals(Arrays.toString(new int[]{15,14,12,16}), Arrays.toString(result[3]));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseMatrix_withEmpty_shouldThrowException() {
        String input = "[[]]";

        MatrixUtils.parseMatrix(input);

        // exception caught jUnit
    }

    @Test(expected = NumberFormatException.class)
    public void parseMatrix_withInvalidChars_shouldThrowException() {
        String input = "[asdfasfd[1,2,]";
        String input2 = null;

        MatrixUtils.parseMatrix(input);
        MatrixUtils.parseMatrix(input2);

        // exception caught by jUnit
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseMatrix_withRectangleMatrix_shouldThrowException() {
        String input = "[[1,2]]";

        MatrixUtils.parseMatrix(input);

        // exception caught by jUnit
    }

    // ================================================================================
    // validateSquare()
    // ================================================================================

    @Test
    public void validateSquare_withSquareMatrix_shouldReturnTrue() {
        int[][] matrix1 = new int[][]{
                {2,2},
                {4,4},
        };
        int[][] matrix2 = new int[][]{
                {1},
        };

        boolean result1 = MatrixUtils.validateSquare(matrix1);
        boolean result2 = MatrixUtils.validateSquare(matrix2);

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void validateSquare_withUnevenMatrix_shouldReturnFalse() {
        int[][] matrix1 = new int[][]{
                {1},
                {65},
                {222},
        };
        int[][] matrix2 = new int[][]{
                {1,2,3,4,5},
        };

        boolean result1 = MatrixUtils.validateSquare(matrix1);
        boolean result2 = MatrixUtils.validateSquare(matrix2);

        assertFalse(result1);
        assertFalse(result2);
    }

    // ================================================================================
    // toString()
    // ================================================================================

    @Test
    public void toString_withMatrix_shouldReturnString() {
        int[][] input = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9},
        };

        String result = MatrixUtils.toString(input);

        assertEquals("[[1, 2, 3],[4, 5, 6],[7, 8, 9]]", result);
    }
}