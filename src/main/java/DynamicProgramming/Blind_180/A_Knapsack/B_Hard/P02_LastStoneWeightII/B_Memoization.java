package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P02_LastStoneWeightII;

public class B_Memoization {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int totalSum = 0;

        for(int i=0; i<n; i++){
            totalSum += stones[i];
        }

        Boolean[][] dp = new Boolean[n+1][totalSum/2 + 1];

        int maxSubsetCapacity = 0;
        for(int s=totalSum/2; s>=1; s--){
            if(helper(stones, n, s, dp)){
                maxSubsetCapacity = s;
                break;
            }
        }

        return totalSum - 2 * maxSubsetCapacity;
    }

    public boolean helper(int[] stones, int n, int capacity, Boolean[][] dp){
        if(capacity == 0){
            return true;
        }

        if(n == 0){
            return false;
        }

        if(dp[n][capacity] != null){
            return dp[n][capacity];
        }

        boolean pick = false;
        if(stones[n-1] <= capacity){
            pick = helper(stones, n-1, capacity - stones[n-1], dp);
        }
        boolean notPick  = helper(stones, n-1, capacity, dp);

        return dp[n][capacity] = pick || notPick;
    }

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}) + " (expected 1)");
        System.out.println(solution.lastStoneWeightII(new int[]{31, 26, 33, 21, 40}) + " (expected 5)");
        System.out.println(solution.lastStoneWeightII(new int[]{1, 2}) + " (expected 1)");
    }
}
