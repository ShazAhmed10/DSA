package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P01_ZeroOneKnapsack;

public class A_RecursiveNaive {
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

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.knapsack01(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 3, 50) + " (expected 220)");
        System.out.println(solution.knapsack01(new int[]{5, 4, 6, 3}, new int[]{10, 40, 30, 50}, 4, 10) + " (expected 90)");
    }
}
