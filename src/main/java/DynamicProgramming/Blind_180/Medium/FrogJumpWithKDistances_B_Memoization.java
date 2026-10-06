package DynamicProgramming.Blind_180.Medium;

import java.util.*;

public class FrogJumpWithKDistances_B_Memoization {
    public int frogJump(int[] heights, int k) {
        int n = heights.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        helper(n, heights, k, dp);
        return dp[n];
    }

    public int helper(int n, int[] heights, int k, int[] dp){
        if(n == 1){
            return dp[n] = 0;
        }

        if(dp[n] != Integer.MAX_VALUE){
            return dp[n];
        }

        for(int i=1; i<=k; i++){
            if(n > i){
                dp[n] = Math.min(dp[n], helper(n-i, heights, k, dp) + Math.abs(heights[n-1] - heights[n-1-i]));
            }
            else {
                break;
            }
        }

        return dp[n];
    }
}
