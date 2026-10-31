package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P04_BestTimeToBuyAndSellStockWithTransactionFee;

public class D_Tabulation_SpaceOptimisation {
    public int maxProfit(int[] prices, int fee) {
        int aheadWithoutHolding = 0;
        int aheadHolding = 0;

        for(int day=prices.length-1; day>=0; day--){
            int pickWithHolding = aheadWithoutHolding + prices[day] - fee;
            int notPickWithHolding = aheadHolding;
            int holdingProfit = Math.max(pickWithHolding, notPickWithHolding);

            int pickWithoutHolding = aheadHolding - prices[day];
            int notPickWithoutHolding = aheadWithoutHolding;
            int notHoldingProfit = Math.max(pickWithoutHolding, notPickWithoutHolding);

            aheadWithoutHolding = notHoldingProfit;
            aheadHolding = holdingProfit;
        }

        return aheadWithoutHolding;
    }

    public static void main(String[] args) {
        D_Tabulation_SpaceOptimisation solution = new D_Tabulation_SpaceOptimisation();
        System.out.println(solution.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2) + " (expected 8)");
        System.out.println(solution.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3) + " (expected 6)");
        System.out.println(solution.maxProfit(new int[]{1, 5, 4, 9}, 2) + " (expected 6)");
        System.out.println(solution.maxProfit(new int[]{1}, 0) + " (expected 0)");
    }
}
