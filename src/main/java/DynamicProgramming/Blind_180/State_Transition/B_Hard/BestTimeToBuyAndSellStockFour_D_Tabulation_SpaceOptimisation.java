package DynamicProgramming.Blind_180.State_Transition.B_Hard;

public class BestTimeToBuyAndSellStockFour_D_Tabulation_SpaceOptimisation {
    public int maxProfit(int k, int[] prices) {
        int[] aheadHolding = new int[k+1];
        int[] aheadNotHolding = new int[k+1];
        int[] currHolding = new int[k+1];
        int[] currNotHolding = new int[k+1];

        for(int day=prices.length-1; day>=0; day--){
            for(int transaction=1; transaction<=k; transaction++){
                int pick = 0;
                int notPick = 0;

                pick = aheadNotHolding[transaction-1] + prices[day];
                notPick = aheadHolding[transaction];
                currHolding[transaction] = Math.max(pick, notPick);

                pick = aheadHolding[transaction] - prices[day];
                notPick = aheadNotHolding[transaction];
                currNotHolding[transaction] = Math.max(pick, notPick);
            }

            int[] temp = aheadHolding;
            aheadHolding = currHolding;
            currHolding = temp;

            temp = aheadNotHolding;
            aheadNotHolding = currNotHolding;
            currNotHolding = temp;
        }

        return aheadNotHolding[k];
    }
}
