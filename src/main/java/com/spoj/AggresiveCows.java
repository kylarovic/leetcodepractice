package com.spoj;

import java.util.Arrays;
import java.util.Scanner;
import org.junit.*;

/**
 * Farmer John has built a new long barn, with N (2 <= N <= 100,000) stalls.
 * The stalls are located along a straight line at positions x1,...,xN (0 <= xi <= 1,000,000,000).

 His C (2 <= C <= N) cows don't like this barn layout and become aggressive towards each other once put into a stall.
 To prevent the cows from hurting each other, FJ want to assign the cows to the stalls, such that the minimum distance
 between any two of them is as large as possible. What is the largest minimum distance?
 Input

 t – the number of test cases, then t test cases follows.
 * Line 1: Two space-separated integers: N and C
 * Lines 2..N+1: Line i+1 contains an integer stall location, xi
 Output

 For each test case output one integer: the largest minimum distance.
 Example

 Input:

 1
 5 3
 1
 2
 8
 4
 9
 Output:

 3
 Output details:

 FJ can put his 3 cows in the stalls at positions 1, 4 and 8,
 resulting in a minimum distance of 3.


 * Created on 5/27/16.
 */
public class AggresiveCows {
    public int search(int[] a, int cows) {
        int result = Integer.MAX_VALUE;

        Arrays.sort(a);
        int low = 1;
        int high = a[a.length-1] - a[0];

        while(low <= high) {
            int mid = low + (high - low) / 2;

            if(canBePlaced(a, mid, cows)) {
                //System.out.println(mid);
                result = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    public boolean canBePlaced(int[] a, int minDistance, int cows) {

        int target = a[0] + minDistance;
        int maxValue = a[a.length-1];
        int required = 1, i = 1;
        while(i < a.length && target <= maxValue) {
            if(a[i] >= target){
                required++;
                target = a[i] +  minDistance;
            }
            if(required == cows) return true;
            i++;
        }
        return false;
    }

    @Test
    public void testMinDistanceCheck() {
        int[] a = {1,2,3,4};
        Assert.assertEquals(true, canBePlaced(a, 3, 2));
        Assert.assertEquals(false, canBePlaced(a, 3, 3));

        int[] b = {1,2,4,8,9};
        Assert.assertEquals(true, canBePlaced(b, 3, 3));
        Assert.assertEquals(true, canBePlaced(b, 7, 2));
        Assert.assertEquals(true, canBePlaced(b, 6, 2));
        Assert.assertEquals(true, canBePlaced(b, 5, 2));
        Assert.assertEquals(true, canBePlaced(b, 4, 2));
        Assert.assertEquals(true, canBePlaced(b, 2, 3));

        int[] c = {3,5,7,9,11,13,15};
        Assert.assertEquals(true, canBePlaced(c, 2, 7));
        Assert.assertEquals(true, canBePlaced(c, 2, 5));

        int[] d = {0,100};
        Assert.assertEquals(true, canBePlaced(d, 100,2));
        Assert.assertEquals(true, canBePlaced(d, 15,2));

        int[] e = {0,6};
        Assert.assertEquals(true, canBePlaced(e, 4, 2));
        Assert.assertEquals(true, canBePlaced(e, 6, 2));
    }
    @Test
    public void testCase() {
        AggresiveCows a = new AggresiveCows();
        int[] a1 = {1,2,3,4};

        Assert.assertEquals(3, a.search(a1, 2));
        Assert.assertEquals(1, a.search(a1, 4));

        int[] a2 = {1,2,4,8,9};
        Assert.assertEquals(3, a.search(a2, 3));
        Assert.assertEquals(1, a.search(a2, 4));

        int[] a3 = {3,5,7,9,11,13,16};
        Assert.assertEquals(2, a.search(a3, 7));
        Assert.assertEquals(4, a.search(a3, 4));
        Assert.assertEquals(2, a.search(a3, 5));

        int[] a4 = {0,3,5};
        Assert.assertEquals(5, a.search(a4, 2));

        int[] a5 = {0,6};
        Assert.assertEquals(6, a.search(a5, 2));
    }

    public static void main(String[] args) {
        AggresiveCows a = new AggresiveCows();
        int[] arr = {1,2,3,4};
        System.out.println(a.search(arr, 2));
//
//        Scanner in = new Scanner(System.in);
//        int size = in.nextInt();
//        for (int i = 0; i < size; i++) {
//            int spots = in.nextInt();
//            int cows = in.nextInt();
//            int[] spotsArray = new int[spots];
//            for(int j = 0; j < spots; j++) {
//                spotsArray[j] = in.nextInt();
//            }
//            System.out.println(a.search(spotsArray, cows));
//        }
    }
}
