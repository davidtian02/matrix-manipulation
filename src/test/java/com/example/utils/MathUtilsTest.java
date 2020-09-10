package com.example.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class MathUtilsTest {

    @Test
    public void isPrime_withPrimeNumbers_shouldReturnTrue() {
        // inputs
        int input1 = 2;
        int input2 = 5;
        int input3 = 2147480791;

        // execution
        boolean result1 = MathUtils.isPrime(input1);
        boolean result2 = MathUtils.isPrime(input2);
        boolean result3 = MathUtils.isPrime(input3);

        // assertions
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }

    @Test
    public void isPrime_withNegativeNumbers_shouldReturnFalse() {
        int input1 = -1;
        int input2 = -50;

        boolean result1 = MathUtils.isPrime(input1);
        boolean result2 = MathUtils.isPrime(input2);

        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void isPrime_withSpecialCaseNumbers_shouldReturnFalse() {
        int input1 = 0;
        int input2 = 1;

        boolean result1 = MathUtils.isPrime(input1);
        boolean result2 = MathUtils.isPrime(input2);

        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void isPrime_withCompositeNumber_shouldReturnFalse() {
        int input1 = 4;
        int input2 = 8;
        int input3 = 2147480792;

        boolean result1 = MathUtils.isPrime(input1);
        boolean result2 = MathUtils.isPrime(input2);
        boolean result3 = MathUtils.isPrime(input3);

        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }
}