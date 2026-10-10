package DynamicProgramming.Blind_180.State_Transition.A_Medium;

import java.util.*;

public class MaximumSumOfNonAdjacentElements_A_Memoization {
    public int nonAdjacent(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        helper(n, nums, dp);
        return dp[n];
    }

    public int helper(int n, int[] nums, int[] dp){
        if(n == 0){
            return dp[n] = 0;
        }
        if(n < 0){
            return 0;
        }

        if(dp[n] != -1){
            return dp[n];
        }

        int pick = nums[n-1] + helper(n-2, nums, dp);
        int not_pick = helper(n-1, nums, dp);

        return dp[n] = Math.max(pick, not_pick);
    }
}
