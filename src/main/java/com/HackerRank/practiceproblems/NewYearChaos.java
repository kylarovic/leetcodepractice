package com.HackerRank.practiceproblems;

import java.util.HashMap;
import java.util.Map;

/**
 * Counting Inversions
 * https://www.hackerrank.com/challenges/new-year-chaos
 * <p>
 * Created on 6/19/16.
 */
public class NewYearChaos {

    boolean isTooChaotic = false;

    public void minBribeNeeded(int[] a, Map<Integer, Integer> map) {
        if (isTooChaotic)
            return;
        if (a.length < 2) {
            return;
        }
        int mid = (a.length) / 2;
        int[] left = new int[mid];
        int[] right = new int[a.length - mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = a[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = a[mid + i];
        }
        minBribeNeeded(left, map);
        minBribeNeeded(right, map);

        if (merge(left, right, a, map)) {

        } else {
            isTooChaotic = true;
            System.out.println("Too chaotic");
        }
        //System.out.println(Arrays.toString(a));
    }

    public boolean merge(int[] a, int[] b, int[] c, Map<Integer, Integer> map) {
        int i = 0, j = 0, k = 0, prev = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k++] = a[i++];
            } else if (a[i] > b[j]) {
                c[k++] = b[j];
                map.put(a[i], map.get(a[i]) + (j + 1 - prev));

                j++;
                prev = j;
                //System.out.println(map);
                if (map.get(a[i]) > 2)
                    return false;
            } else {
                // all ids are unique.. so it doesn't come here.
            }
        }
        while (i < a.length) {
            c[k++] = a[i++];
        }

        while (j < b.length) {
            c[k++] = b[j++];
        }
        return true;
    }

    // Slower version, but linear in this case since if
    // inversion > 2, then breaking out.
    public static void insertionSort(int[] a) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 1; i < a.length; i++) {

            int j = i;
            int limit = 0;
            while (j > 0 && a[j] < a[j - 1]) {

                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;

                if (!isChaotic(map, a, j)) {
                    System.out.println("Too chaotic");
                    return;
                }
                j--;
                limit++;
            }
            count = count + limit;
        }
        System.out.println(count);
    }

    public static boolean isChaotic(Map<Integer, Integer> map, int[] a, int j) {
        if (map.containsKey(a[j])) {
            if (map.get(a[j]) == 2) {

                return false;
            }
            map.put(a[j], map.get(a[j]) + 1);
        } else {
            map.put(a[j], 1);
        }
        return true;
    }

    public void start() {
        int[] a = { 2, 1, 5, 3, 4 };
        int[] b = { 2, 5, 1, 3, 4 };
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 5; i++) {
            map.put(i + 1, 0);
        }
        minBribeNeeded(a, map);
        //minBribeNeeded(b, map);
        int total = 0;
        for (Integer each : map.values()) {
            total = total + each;
        }
        System.out.print(total);
    }

    public static void main(String[] args) {

        NewYearChaos n = new NewYearChaos();
        n.start();
    }
}
