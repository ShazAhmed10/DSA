package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P01_PartitionSetIntoTwoSubsetsMinDifference;

public class Dc_TabulationSpaceOptimisation {
    public int minDifference(int[] arr, int n) {
        int total = 0;
        for(int i=0; i<n; i++){
            total += arr[i];
        }

        int[] dp = new int[total/2 + 1];

        for(int i=1; i<=n; i++){
            for(int capacity=total/2; capacity >= arr[i-1]; capacity--){
                int pick = arr[i-1] + dp[capacity - arr[i-1]];
                int notPick = dp[capacity];

                dp[capacity] = Math.max(pick, notPick);
            }
        }

        return total - (2 * dp[total/2]);
    }

    public static void main(String[] args) {
        Dc_TabulationSpaceOptimisation solution = new Dc_TabulationSpaceOptimisation();
        System.out.println(solution.minDifference(new int[]{1, 7, 14, 5}, 4) + " (expected 1)");
        System.out.println(solution.minDifference(new int[]{3, 1, 6, 2, 2}, 5) + " (expected 0)");
        System.out.println(solution.minDifference(new int[]{1}, 1) + " (expected 1)");
    }
}
