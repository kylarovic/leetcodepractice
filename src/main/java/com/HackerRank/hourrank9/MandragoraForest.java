package com.HackerRank.hourrank9;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/hourrank-9/challenges/mandragora

 * Created on 6/12/16.
 */
public class MandragoraForest {

    // Exponential solution
    public long maxPoints(long[] a, int i, long strength, long experience) {
        if (i == a.length) {
            return experience;
        }

        long eat = maxPoints(a, i + 1, strength + 1, experience);
        long defeat = maxPoints(a, i + 1, strength, experience + (strength * a[i]));

        return Math.max(eat, defeat);
    }

    // O(n) solution
    /*
    Example:
    H = [2,3,4,5]
    Have to make a cut, such that the result is max. Check all possible cuts.
    Case : 1 : no cut - means, defeat all - [2 + 3 + 4 + 5] = 14
    Case : 2 : cut after H[0] - [2 * (3 + 4 + 5)] = 24
    Case : 3 : cut after H[1] - [3 * (4 + 5)] = 27
    Case : 4 : cut after H[2] - [4 * 5] = 20

    Basically calculate postfix sum, and multiply by 1, 2, 3 ... N;

     */
    public long maxPoints(long[] a) {
        long result = 0L;
        // calculate postfix sums
        long[] sums = new long[a.length];
        sums[a.length-1] = a[a.length-1];
        for(int i = a.length-2; i >= 0; i--) {
            sums[i] = sums[i+1] + a[i];
        }

        // Make cuts - left side eat, right side defeat
        for(int i = 1; i <= a.length; i++) {
            result = Math.max(result, i * sums[i-1]);
        }
        return result;
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();


        for(int idx = 0; idx < T; idx++) {
            int N = in.nextInt();
            long[] a = new long[N];
            for(int i = 0; i < N; i++) {
                if(in.hasNext())a[i] = in.nextLong();
            }
            Arrays.sort(a);
            System.out.println(maxPoints(a));
        }
    }

    public static void main(String[] args) {
        MandragoraForest m = new MandragoraForest();
        m.start();
    }
}
