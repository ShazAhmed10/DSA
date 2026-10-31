package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P02_LastStoneWeightII;

public class Da_TabulationSpaceOptimisation {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int totalSum = 0;

        for(int i=0; i<n; i++){
            totalSum += stones[i];
        }

        boolean[] prev = new boolean[totalSum/2 + 1];
        boolean[] curr = new boolean[totalSum/2 + 1];

        prev[0] = true;
        curr[0] = true;
        for(int i=1; i<=n; i++){
            for(int s=1; s<=totalSum/2; s++){
                boolean pick = false;
                if(stones[i-1] <= s){
                    pick = prev[s - stones[i-1]];
                }
                boolean notPick = prev[s];

                curr[s] = pick || notPick;
            }
            boolean[] temp = prev;
            prev = curr;
            curr = temp;
        }

        int maxSubsetCapacity = 0;
        for(int s=totalSum/2; s>=0; s--){
            if(prev[s]){
                maxSubsetCapacity = s;
                break;
            }
        }

        return totalSum - 2 * maxSubsetCapacity;
    }

    public static void main(String[] args) {
        Da_TabulationSpaceOptimisation solution = new Da_TabulationSpaceOptimisation();
        System.out.println(solution.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}) + " (expected 1)");
        System.out.println(solution.lastStoneWeightII(new int[]{31, 26, 33, 21, 40}) + " (expected 5)");
        System.out.println(solution.lastStoneWeightII(new int[]{1, 2}) + " (expected 1)");
    }
}
