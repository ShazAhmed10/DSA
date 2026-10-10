package DynamicProgramming.Blind_180.State_Transition.A_Medium;

public class BestTimeToBuyAndSellStockWithTransactionFee_D_Tabulation_SpaceOptimisation {
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
}
