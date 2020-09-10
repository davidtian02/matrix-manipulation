package com.example.utils;

import org.apache.commons.math3.primes.Primes;

public class MathUtils {
    public static boolean isPrime(int input) {
        return Primes.isPrime(input);
    }
}
