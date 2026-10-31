package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P01_FrogJumpWithKDistances;

import java.util.*;

public class B_Memoization {
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

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.frogJump(new int[]{10, 5, 20, 0, 15}, 2) + " (expected 15)");
        System.out.println(solution.frogJump(new int[]{15, 4, 1, 14, 15}, 3) + " (expected 2)");
    }
}
