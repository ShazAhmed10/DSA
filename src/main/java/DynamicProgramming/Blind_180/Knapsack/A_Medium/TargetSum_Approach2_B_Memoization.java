package DynamicProgramming.Blind_180.Knapsack.A_Medium;

import java.util.*;

public class TargetSum_Approach2_B_Memoization {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for(int i=0; i<n; i++){
            total += nums[i];
        }

        int redEqSum = total + target;
        if(redEqSum < 0 || (redEqSum % 2) != 0){
            return 0;
        }

        int[][] dp = new int[n+1][redEqSum/2 + 1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(nums, n, redEqSum/2, dp);
    }

    public int helper(int[] nums, int n, int p, int[][] dp){
        if(n == 0){
            return dp[n][p] = (p == 0) ? 1 : 0;
        }

        if(dp[n][p] != -1){
            return dp[n][p];
        }

        int pick = 0;
        if(nums[n-1] <= p){
            pick = helper(nums, n-1, p - nums[n-1], dp);
        }
        int notPick = helper(nums, n-1, p, dp);

        return dp[n][p] = pick + notPick;
    }
}
