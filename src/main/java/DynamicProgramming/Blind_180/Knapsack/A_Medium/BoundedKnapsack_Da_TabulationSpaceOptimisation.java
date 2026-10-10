package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class BoundedKnapsack_Da_TabulationSpaceOptimisation {
    public int knapsack01(int[] wt, int[] val, int n, int W) {
        int[] curr = new int[W+1];
        int[] prev = new int[W+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=W; j++){
                int pick = 0;
                int notPick = 0;
                if(wt[i-1] <= j){
                    pick = val[i-1] + prev[j-wt[i-1]];
                }
                notPick = prev[j];
                curr[j] = Math.max(pick, notPick);
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[W];
    }
}
