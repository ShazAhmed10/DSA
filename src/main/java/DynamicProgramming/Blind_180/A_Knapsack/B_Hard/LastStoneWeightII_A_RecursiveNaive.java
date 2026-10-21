package DynamicProgramming.Blind_180.A_Knapsack.B_Hard;

public class LastStoneWeightII_A_RecursiveNaive {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int totalSum = 0;

        for(int i=0; i<n; i++){
            totalSum += stones[i];
        }

        int maxSubsetCapacity = 0;
        for(int s=totalSum/2; s>=1; s--){
            if(helper(stones, n, s)){
                maxSubsetCapacity = s;
                break;
            }
        }

        return totalSum - 2 * maxSubsetCapacity;
    }

    public boolean helper(int[] stones, int n, int capacity){
        if(capacity == 0){
            return true;
        }

        if(n == 0){
            return false;
        }

        boolean pick = false;
        if(stones[n-1] <= capacity){
            pick = helper(stones, n-1, capacity - stones[n-1]);
        }
        boolean notPick  = helper(stones, n-1, capacity);

        return pick || notPick;
    }
}
