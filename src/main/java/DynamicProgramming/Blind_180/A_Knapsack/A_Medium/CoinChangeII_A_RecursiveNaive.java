package DynamicProgramming.Blind_180.A_Knapsack.A_Medium;

public class CoinChangeII_A_RecursiveNaive {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        return helper(coins, n, amount);
    }

    public int helper(int[] coins, int n, int targetCapacity){
        if(targetCapacity == 0){
            return 1;
        }

        if(n == 0){
            return 0;
        }

        int pick = 0;
        if(coins[n-1] <= targetCapacity){
            pick = helper(coins, n, targetCapacity - coins[n-1]);
        }
        int notPick = helper(coins, n-1, targetCapacity);

        return pick + notPick;
    }
}
