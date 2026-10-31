package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P02_LastStoneWeightII;

public class Dc_TabulationSpaceOptimisation {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int totalSum = 0;

        for(int i=0; i<n; i++){
            totalSum += stones[i];
        }

        boolean[] dp = new boolean[totalSum/2 + 1];

        dp[0] = true;
        for(int i=1; i<=n; i++){
            for(int s=totalSum/2; s>=stones[i-1]; s--){
                boolean pick = dp[s - stones[i-1]];
                boolean notPick = dp[s];

                dp[s] = pick || notPick;
            }
        }

        int maxSubsetCapacity = 0;
        for(int s=totalSum/2; s>=0; s--){
            if(dp[s]){
                maxSubsetCapacity = s;
                break;
            }
        }

        return totalSum - 2 * maxSubsetCapacity;
    }

    public static void main(String[] args) {
        Dc_TabulationSpaceOptimisation solution = new Dc_TabulationSpaceOptimisation();
        System.out.println(solution.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}) + " (expected 1)");
        System.out.println(solution.lastStoneWeightII(new int[]{31, 26, 33, 21, 40}) + " (expected 5)");
        System.out.println(solution.lastStoneWeightII(new int[]{1, 2}) + " (expected 1)");
    }
}
