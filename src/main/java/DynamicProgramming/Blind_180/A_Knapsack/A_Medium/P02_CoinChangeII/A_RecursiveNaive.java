package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P02_CoinChangeII;

public class A_RecursiveNaive {
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

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.change(5, new int[]{1, 2, 5}) + " (expected 4)");
        System.out.println(solution.change(3, new int[]{2}) + " (expected 0)");
        System.out.println(solution.change(10, new int[]{10}) + " (expected 1)");
    }
}
