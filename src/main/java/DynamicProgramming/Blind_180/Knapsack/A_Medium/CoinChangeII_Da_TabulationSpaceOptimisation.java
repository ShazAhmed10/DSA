package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class CoinChangeII_Da_TabulationSpaceOptimisation {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount+1];
        int[] curr = new int[amount+1];

        prev[0] = 1;
        curr[0] = 1;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=amount; j++){
                int pick = 0;
                if(coins[i-1] <= j){
                    pick = curr[j - coins[i-1]];
                }
                int notPick = prev[j];

                curr[j] = pick + notPick;
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[amount];
    }
}
