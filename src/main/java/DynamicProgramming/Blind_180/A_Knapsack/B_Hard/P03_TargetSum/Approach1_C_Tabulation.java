package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P03_TargetSum;

public class Approach1_C_Tabulation {
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

    public static void main(String[] args) {
        Approach1_C_Tabulation solution = new Approach1_C_Tabulation();
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3) + " (expected 5)");
        System.out.println(solution.findTargetSumWays(new int[]{1}, 1) + " (expected 1)");
        System.out.println(solution.findTargetSumWays(new int[]{0, 0}, 0) + " (expected 4)");
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1}, 2) + " (expected 0)");
    }
}
