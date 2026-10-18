package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class TargetSum_Approach1_C_Tabulation {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        if(Math.abs(target) > totalSum){
            return 0;
        }

        int[][] dp = new int[n+1][2 * totalSum + 1];

        for(int curr = -1 * totalSum; curr <= totalSum; curr++){
            if(curr == target){
                dp[0][curr + totalSum] = 1;
            }
            else{
                dp[0][curr + totalSum] = 0;
            }
        }

        for(int i=1; i<=n; i++){
            for(int curr = -1 * totalSum; curr <= totalSum; curr++){
                int index1 = (curr - nums[i-1]) + totalSum;
                int way1 = 0;
                if(index1 >= 0 && index1 <= 2*totalSum){
                    way1 = dp[i-1][index1];
                }

                int index2 = curr + nums[i-1] + totalSum;
                int way2 = 0;
                if(index2 >= 0 && index2 <= 2*totalSum){
                    way2 = dp[i-1][index2];
                }

                dp[i][curr + totalSum] = way1 + way2;
            }
        }

        return dp[n][totalSum];
    }
}