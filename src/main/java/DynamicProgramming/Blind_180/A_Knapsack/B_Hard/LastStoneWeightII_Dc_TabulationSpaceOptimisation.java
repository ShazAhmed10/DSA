package DynamicProgramming.Blind_180.A_Knapsack.B_Hard;

public class LastStoneWeightII_Dc_TabulationSpaceOptimisation {
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
}
