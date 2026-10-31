package DynamicProgramming.Blind_180.D_Interval.B_Hard.P01_MatrixChainMultiplication;

public class C_Tabulation {
    public int matrixMultiplication(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for(int left = n-1; left >= 0; left--){
            for(int right = left+2; right < n; right++){
                int currMinMultiplyCost = Integer.MAX_VALUE;

                for(int i = left+1; i < right; i++){
                    int currLeftCost = dp[left][i];
                    int currRightCost = dp[i][right];
                    int currCost = nums[i] * nums[left] * nums[right];
                    int currTotalCost = currLeftCost + currRightCost + currCost;

                    currMinMultiplyCost = Math.min(currTotalCost, currMinMultiplyCost);
                }

                dp[left][right] = currMinMultiplyCost;
            }
        }

        return dp[0][n-1];
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.matrixMultiplication(new int[]{10, 15, 20, 25}) + " (expected 8000)");
        System.out.println(solution.matrixMultiplication(new int[]{4, 2, 3}) + " (expected 24)");
        System.out.println(solution.matrixMultiplication(new int[]{10, 15, 20, 25, 30}) + " (expected 15500)");
        System.out.println(solution.matrixMultiplication(new int[]{5, 10}) + " (expected 0)");
    }
}
