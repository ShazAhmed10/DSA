package DynamicProgramming.Blind_180.C_Sequence.B_Hard;

public class LongestIncreasingSubsequence_Approach2_C_Tabulation {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        int best = 0;
        for(int i=0; i<n; i++){
            int currLength = 1;
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i]){
                    currLength = Math.max(currLength, 1 + dp[j]);
                }
            }
            dp[i] = currLength;
            best = Math.max(best, dp[i]);
        }

        return best;
    }
}
