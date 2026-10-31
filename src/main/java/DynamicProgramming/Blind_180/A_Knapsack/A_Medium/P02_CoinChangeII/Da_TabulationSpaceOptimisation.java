package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P02_CoinChangeII;

public class Da_TabulationSpaceOptimisation {
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

    public static void main(String[] args) {
        Da_TabulationSpaceOptimisation solution = new Da_TabulationSpaceOptimisation();
        System.out.println(solution.change(5, new int[]{1, 2, 5}) + " (expected 4)");
        System.out.println(solution.change(3, new int[]{2}) + " (expected 0)");
        System.out.println(solution.change(10, new int[]{10}) + " (expected 1)");
    }
}
