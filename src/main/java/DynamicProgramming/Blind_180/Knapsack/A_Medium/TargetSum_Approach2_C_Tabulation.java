package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class TargetSum_Approach2_C_Tabulation {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for(int i=0; i<n; i++){
            total += nums[i];
        }

        if(Math.abs(target) > total){
            return 0;
        }

        int redEqSum = total + target;
        if(redEqSum < 0 || (redEqSum % 2) != 0){
            return 0;
        }

        int p = redEqSum/2;
        int[][] dp = new int[n+1][p+1];

        dp[0][0] = 1;
        for(int i=1; i<=n; i++){
            for(int j=0; j<=p; j++){
                int pick = 0;
                if(nums[i-1] <= j){
                    pick = dp[i-1][j - nums[i-1]];
                }
                int notPick = dp[i-1][j];

                dp[i][j] = pick + notPick;
            }
        }

        return dp[n][p];
    }
}
