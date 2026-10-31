package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P02_CoinChangeII;

public class C_Tabulation {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];

        for(int i=0; i<=n; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=amount; j++){
                int pick = 0;
                if(coins[i-1] <= j){
                    pick = dp[i][j - coins[i-1]];
                }
                int notPick = dp[i-1][j];

                dp[i][j] = pick + notPick;
            }
        }

        return dp[n][amount];
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.change(5, new int[]{1, 2, 5}) + " (expected 4)");
        System.out.println(solution.change(3, new int[]{2}) + " (expected 0)");
        System.out.println(solution.change(10, new int[]{10}) + " (expected 1)");
    }
}
