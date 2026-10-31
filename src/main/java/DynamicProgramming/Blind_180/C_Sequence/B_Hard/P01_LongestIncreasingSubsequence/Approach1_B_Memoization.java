package DynamicProgramming.Blind_180.C_Sequence.B_Hard.P01_LongestIncreasingSubsequence;

import java.util.Arrays;

public class Approach1_B_Memoization {
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

    public static void main(String[] args) {
        Approach1_B_Memoization solution = new Approach1_B_Memoization();
        System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{7, 7, 7, 7}) + " (expected 1)");
    }
}
