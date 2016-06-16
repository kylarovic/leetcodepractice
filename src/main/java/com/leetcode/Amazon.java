package com.leetcode;

import org.junit.Assert;

import java.util.Arrays;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * Created on 6/12/16.
 */
public class Amazon {
    public int[] mergeArrays(int[] a, int[] b) {

        int i = 0;
        int j = 0;
        int k = 0;
        int[] c = new int[a.length + b.length];
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k++] = a[i++];
            } else if (a[i] > b[j]) {
                c[k++] = b[j++];
            } else {
                c[k++] = b[j++];
                c[k++] = a[i++];
            }
        }
        while (i < a.length) {
            c[k++] = a[i++];
        }
        while (j < a.length) {
            c[k++] = b[j++];
        }
        return c;
    }

    public int sigma(int a, int b) {
        int n = b - a;
        return (n * (n + 1)) / 2;
    }

    public int giveCandy(int[] a) {
        int result = 0;
        // move left to right
        int leftToRight[] = new int[a.length];
        int rightToLeft[] = new int[a.length];

        leftToRight[0] = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] < a[i]) {
                leftToRight[i] = leftToRight[i - 1] + 1;
            } else if (a[i - 1] > a[i]) {
                leftToRight[i] = 1;
            } else {
                // when equal keep same
                leftToRight[i] = 1;
            }
        }

        rightToLeft[a.length - 1] = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] < a[i + 1]) {
                rightToLeft[i] = 1;
            } else if (a[i] > a[i + 1]) {
                rightToLeft[i] = rightToLeft[i + 1] + 1;
            } else {
                // when equal increment
                rightToLeft[i] = 1;
            }
        }

        int resultCandy[] = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            resultCandy[i] = Math.max(rightToLeft[i], leftToRight[i]);
            result = result + resultCandy[i];
        }
        System.out.println(Arrays.toString(resultCandy));
        return result;
    }

    public void start() {
        int[] a = { 1, 2, 3, 4 };
        int[] b = { 2, 4, 2, 6, 1, 7, 8, 9, 2, 1 };
        int[] c = { 2, 2 };
        int[] d = { 1, 2, 2 };

        Assert.assertTrue(10 == giveCandy(a));
        Assert.assertTrue(19 == giveCandy(b));
        Assert.assertTrue(2 == giveCandy(c));
        Assert.assertTrue(4 == giveCandy(d));
    }

    public static void main(String[] args) {
        Amazon a = new Amazon();
        a.start();
    }
}
