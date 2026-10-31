package DynamicProgramming.Blind_180.C_Sequence.B_Hard.P01_LongestIncreasingSubsequence;

public class Approach2_C_Tabulation {
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

    public static void main(String[] args) {
        Approach2_C_Tabulation solution = new Approach2_C_Tabulation();
        System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{7, 7, 7, 7}) + " (expected 1)");
    }
}
