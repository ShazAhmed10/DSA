package DynamicProgramming.Blind_180.B_State_Transition.B_Hard.P02_BestTimeToBuyAndSellStockIV;

public class A_RecursiveNaive {
    public int maxProfit(int k, int[] prices) {
        int day = 0;
        return helper(prices, day, 0, k);
    }

    public int helper(int[] prices, int day, int holding, int k){
        if(day > prices.length - 1 || k == 0){
            return 0;
        }

        int pick = 0;
        int notPick = 0;
        if(holding == 1){
            pick = helper(prices, day+1, 0, k-1) + prices[day];
            notPick = helper(prices, day+1, 1, k);
        }
        else{
            pick = helper(prices, day+1, 1, k) - prices[day];
            notPick = helper(prices, day+1, 0, k);
        }

        return Math.max(pick, notPick);
    }

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.maxProfit(2, new int[]{2, 4, 1}) + " (expected 2)");
        System.out.println(solution.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}) + " (expected 7)");
        System.out.println(solution.maxProfit(2, new int[]{2, 1, 4, 5, 2, 9, 7}) + " (expected 11)");
    }
}
