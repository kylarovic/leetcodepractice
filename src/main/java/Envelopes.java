import com.leetcode.Utils.Utility;

import java.util.*;

/**
 * Created on 5/26/16.
 */
public class Envelopes {
    public int envelopes(int[][] env) {
        int result = 0;
        Arrays.sort(env, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0])
                    return -1;
                if (o1[0] > o2[0])
                    return 1;
                if (o1[1] > o2[1])
                    return -1;
                if (o1[1] < o2[1])
                    return 1;
                return 0;
            }
        });

        List<Integer> widths = new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        widths.add(env[0][1]);
        for(int i = 1; i < env.length; i++) {
            if(env[i-1][0] != env[i][0]) {
                widths.add(env[i][1]);
            }
        }

        for(int i = 0; i < widths.size(); i++) {
            int idx = binarySearch(res, widths.get(i));
            if(idx < res.size()) {
                res.set(idx, widths.get(i));
            }else {
                res.add(widths.get(i));
            }
            System.out.println(res);
        }
        //System.out.println(widths);
        //Utility.printArray(env);
        return result;
    }

    public int binarySearch(List<Integer> result, int key) {
        int low = 0, high = result.size()-1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(result.get(mid) == key) {
                if(mid == 0) return mid; // last element so return
                if(result.get(mid-1) == key) {
                    high = mid - 1; // moving left
                }else {
                    return mid;
                }
            }else if (result.get(mid) < key) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        if(low == result.size()-1 && result.get(low) < key) {
            return low + 1; // if key not found, and low points to last element.
        }
        return low;
    }

    public static void main(String[] args) {
        Envelopes e = new Envelopes();
        int[][] env = new int[][] {
                {8, 3},
                {10, 9},
                {15, 7},
                {4,6},
                {10,9},
                {800,12}
        };
        e.envelopes(env);
    }
}
