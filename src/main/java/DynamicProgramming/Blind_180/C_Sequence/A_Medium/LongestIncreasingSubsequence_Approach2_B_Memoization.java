package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestIncreasingSubsequence_Approach2_B_Memoization {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int best = 0;
        for(int i=0; i<n; i++){
            best = Math.max(best, helper(nums, i, dp));
        }

        return best;
    }

    public int helper(int[] nums, int i, int[] dp){
        if(dp[i] != 0){
            return dp[i];
        }

        int currLength = 1;
        for(int j=0; j<i; j++){
            if(nums[j] < nums[i]){
                currLength = Math.max(currLength, 1 + helper(nums, j, dp));
            }
        }

        return dp[i] = currLength;
    }
}
