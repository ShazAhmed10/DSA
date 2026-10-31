package DynamicProgramming.Blind_180.C_Sequence.B_Hard.P01_LongestIncreasingSubsequence;

public class Approach2_B_Memoization {
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

    public static void main(String[] args) {
        Approach2_B_Memoization solution = new Approach2_B_Memoization();
        System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{7, 7, 7, 7}) + " (expected 1)");
    }
}
