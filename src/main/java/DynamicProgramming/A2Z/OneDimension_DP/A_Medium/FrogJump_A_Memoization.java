package DynamicProgramming.A2Z.OneDimension_DP.A_Medium;

import java.util.*;

public class FrogJump_A_Memoization {
    public int frogJump(int[] heights) {
        int n = heights.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, 0);
        helper(n, heights, dp);
        return dp[n];
    }

    public int helper(int n, int[] heights, int[] dp){
        if(n == 1){
            return 0;
        }

        if(dp[n] != 0){
            return dp[n];
        }

        int way_1 = helper(n-1, heights, dp) + Math.abs(heights[n-1] - heights[n-2]);
        int way_2 = Integer.MAX_VALUE;
        if(n > 2){
            way_2 = helper(n-2, heights, dp) + Math.abs(heights[n-1] - heights[n-3]);
        }

        return dp[n] = Math.min(way_1, way_2);
    }
}
