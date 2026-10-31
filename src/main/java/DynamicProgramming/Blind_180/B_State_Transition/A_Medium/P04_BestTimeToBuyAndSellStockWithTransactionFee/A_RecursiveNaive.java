package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P04_BestTimeToBuyAndSellStockWithTransactionFee;

public class A_RecursiveNaive {
    public int maxProfit(int[] prices, int fee) {
        int day = 0;
        return helper(prices, fee, day, 0);
    }

    public int helper(int[] prices, int fee, int day, int holding){
        if(day > prices.length - 1){
            return 0;
        }

        int pick = 0;
        int not_pick = 0;
        if(holding == 1){
            pick = helper(prices, fee, day+1, 0) + prices[day] - fee;
            not_pick = helper(prices, fee, day+1, 1);
        }
        else{
            pick = helper(prices, fee, day+1, 1) - prices[day];
            not_pick = helper(prices, fee, day+1, 0);
        }

        return Math.max(pick, not_pick);
    }

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2) + " (expected 8)");
        System.out.println(solution.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3) + " (expected 6)");
        System.out.println(solution.maxProfit(new int[]{1, 5, 4, 9}, 2) + " (expected 6)");
        System.out.println(solution.maxProfit(new int[]{1}, 0) + " (expected 0)");
    }
}
