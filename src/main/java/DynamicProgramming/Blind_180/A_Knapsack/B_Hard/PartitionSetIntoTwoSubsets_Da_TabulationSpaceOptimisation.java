package DynamicProgramming.Blind_180.A_Knapsack.B_Hard;

public class PartitionSetIntoTwoSubsets_Da_TabulationSpaceOptimisation {
    public int minDifference(int[] arr, int n) {
        int total = 0;
        for(int i=0; i<n; i++){
            total += arr[i];
        }

        int[] prev = new int[total/2 + 1];
        int[] curr = new int[total/2 + 1];

        for(int i=1; i<=n; i++){
            for(int capacity=1; capacity <= total/2; capacity++){
                int pick = 0;
                if(arr[i-1] <= capacity){
                    pick = arr[i-1] + prev[capacity - arr[i-1]];
                }
                int notPick = prev[capacity];

                curr[capacity] = Math.max(pick, notPick);
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }


        return total - (2 * prev[total/2]);
    }
}
