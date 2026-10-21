package DynamicProgramming.Blind_180.A_Knapsack.B_Hard;

public class PartitionSetIntoTwoSubsets_A_RecursiveNaive {
    public int minDifference(int[] arr, int n) {
        int total = 0;
        for(int i=0; i<n; i++){
            total += arr[i];
        }

        int subset = helper(arr, n, total/2);

        return total - (2 * subset);
    }

    public int helper(int[] arr, int n, int capacity){
        if(n == 0 || capacity == 0){
            return 0;
        }

        int pick = 0;
        if(arr[n-1] <= capacity){
            pick = arr[n-1] + helper(arr, n-1, capacity - arr[n-1]);
        }
        int notPick = helper(arr, n-1, capacity);

        return Math.max(pick, notPick);
    }
}
