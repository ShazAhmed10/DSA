package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class BoundedKnapsack_A_RecursiveNaive {
    public int knapsack01(int[] wt, int[] val, int n, int W) {
        return helper(wt, val, n-1, W);
    }

    public int helper(int[] wt, int[] val, int n, int w){
        if(n < 0 || w <= 0){
            return 0;
        }

        int pick = 0;
        if(wt[n] <= w){
            pick = val[n] + helper(wt, val, n-1, w - wt[n]);
        }
        int notPick = helper(wt, val, n-1, w);

        return Math.max(pick, notPick);
    }
}
