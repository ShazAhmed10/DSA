package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P02_CoinChangeII;

public class Db_TabulationSpaceOptimisation {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount+1];

        dp[0] = 1;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=amount; j++){
                int pick = 0;
                if(coins[i-1] <= j){
                    pick = dp[j - coins[i-1]];
                }
                int notPick = dp[j];

                dp[j] = pick + notPick;
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        Db_TabulationSpaceOptimisation solution = new Db_TabulationSpaceOptimisation();
        System.out.println(solution.change(5, new int[]{1, 2, 5}) + " (expected 4)");
        System.out.println(solution.change(3, new int[]{2}) + " (expected 0)");
        System.out.println(solution.change(10, new int[]{10}) + " (expected 1)");
    }
}
