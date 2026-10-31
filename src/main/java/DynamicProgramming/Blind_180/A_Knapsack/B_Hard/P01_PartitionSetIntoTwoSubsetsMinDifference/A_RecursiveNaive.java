package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P01_PartitionSetIntoTwoSubsetsMinDifference;

public class A_RecursiveNaive {
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

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.minDifference(new int[]{1, 7, 14, 5}, 4) + " (expected 1)");
        System.out.println(solution.minDifference(new int[]{3, 1, 6, 2, 2}, 5) + " (expected 0)");
        System.out.println(solution.minDifference(new int[]{1}, 1) + " (expected 1)");
    }
}
