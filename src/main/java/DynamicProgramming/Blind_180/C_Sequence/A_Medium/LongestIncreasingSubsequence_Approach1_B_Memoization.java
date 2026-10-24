package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

import java.util.Arrays;

public class LongestIncreasingSubsequence_Approach1_B_Memoization {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n+1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(nums, 0, -1, dp);
    }

    public int helper(int[] nums, int curr_index, int last_index, int[][] dp){
        if(curr_index == nums.length){
            return 0;
        }

        if(dp[curr_index][last_index + 1] != -1){
            return dp[curr_index][last_index + 1];
        }

        int pick = 0;
        if(last_index == -1 || nums[last_index] < nums[curr_index]){
            pick = 1 + helper(nums, curr_index + 1, curr_index, dp);
        }
        int notPick = helper(nums, curr_index + 1, last_index, dp);

        return dp[curr_index][last_index + 1] = Math.max(pick, notPick);
    }
}
