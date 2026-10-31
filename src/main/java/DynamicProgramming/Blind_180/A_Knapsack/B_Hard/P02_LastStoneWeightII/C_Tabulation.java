package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P02_LastStoneWeightII;

public class C_Tabulation {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int totalSum = 0;

        for(int i=0; i<n; i++){
            totalSum += stones[i];
        }

        boolean[][] dp = new boolean[n+1][totalSum/2 + 1];

        for(int i=0; i<=n; i++){
            dp[i][0] = true;
        }

        for(int i=1; i<=n; i++){
            for(int s=1; s<=totalSum/2; s++){
                boolean pick = false;
                if(stones[i-1] <= s){
                    pick = dp[i-1][s - stones[i-1]];
                }
                boolean notPick = dp[i-1][s];

                dp[i][s] = pick || notPick;
            }
        }

        int maxSubsetCapacity = 0;
        for(int s=totalSum/2; s>=1; s--){
            if(dp[n][s]){
                maxSubsetCapacity = s;
                break;
            }
        }

        return totalSum - 2 * maxSubsetCapacity;
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}) + " (expected 1)");
        System.out.println(solution.lastStoneWeightII(new int[]{31, 26, 33, 21, 40}) + " (expected 5)");
        System.out.println(solution.lastStoneWeightII(new int[]{1, 2}) + " (expected 1)");
    }
}
