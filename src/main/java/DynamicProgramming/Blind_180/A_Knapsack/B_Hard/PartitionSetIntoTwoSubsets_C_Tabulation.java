package DynamicProgramming.Blind_180.A_Knapsack.B_Hard;

public class PartitionSetIntoTwoSubsets_C_Tabulation {
    public int minDifference(int[] arr, int n) {
        int total = 0;
        for(int i=0; i<n; i++){
            total += arr[i];
        }

        int[][] dp = new int[n+1][total/2 + 1];

        for(int i=1; i<=n; i++){
            for(int capacity=1; capacity <= total/2; capacity++){
                int pick = 0;
                if(arr[i-1] <= capacity){
                    pick = arr[i-1] + dp[i-1][capacity - arr[i-1]];
                }
                int notPick = dp[i-1][capacity];

                dp[i][capacity] = Math.max(pick, notPick);
            }
        }


        return total - (2 * dp[n][total/2]);
    }
}
