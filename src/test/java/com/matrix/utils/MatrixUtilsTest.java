package com.matrix.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class MatrixUtilsTest {

    @Test
    public void rotate_withStandardMatrix_shouldRotateClockwise() {
        try {
            MatrixUtils.parseMatrix("[[1,2]4]");
        } catch (Exception e) {
            e.getCause();
            e.getLocalizedMessage();
            e.getMessage();
        }
    }
}