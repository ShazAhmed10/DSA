package DynamicProgramming.Blind_180.Medium;

public class MaximumSumOfNonAdjacentElements_C_Tabulation_SpaceOptimisation {
    public int nonAdjacent(int[] nums) {
        int n = nums.length;

        int prev2 = 0;
        int prev = nums[0];

        for(int i=2; i<=n; i++){
            int pick = nums[i-1] + prev2;
            int not_pick = prev;

            int curr = Math.max(pick, not_pick);

            prev2 = prev;
            prev = curr;
        }

        return prev;
    }
}
