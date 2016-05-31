import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created on 5/26/16.
 */
public class Envelopes {

    public int envelopes(int[][] env) {
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

        int[] dp = new int[env.length];
        int result = 0;
        Arrays.fill(dp, 1);
        for(int i = 0; i < env.length; i++) {
            for(int j = 0; j < i; j++) {
                if(env[i][1] > env[j][1] && env[i][0] > env[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                result = Math.max(dp[i], result);
            }
        }
        return result;
    }
    @Test
    public void test() {
        int[][] env = new int[][] {
                {2,1},
                {3,2},
                {4,3},
                {5,3},
                {5,4},
                {5,5},
                {6,5},
                {7,6},
                {8,7},
                {9,1}
        };
        Assert.assertEquals(7, envelopes(env));

        env = new int[][] {
                {2,100},
                {3,2},
                {4,3},
                {5,3},
                {5,4},
                {5,5}
        };
        Assert.assertEquals(3, envelopes(env));

        env = new int[][] {
                {2,100},{3,200},{4,300},{5,250},{5,400},{5,500},{6,350},{6,360},{7,380}
        };
        Assert.assertEquals(5, envelopes(env));
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
