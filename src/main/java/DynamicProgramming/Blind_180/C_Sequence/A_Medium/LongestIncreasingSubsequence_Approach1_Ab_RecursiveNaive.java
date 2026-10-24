package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestIncreasingSubsequence_Approach1_Ab_RecursiveNaive {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int best = 0;

        for(int i=0; i<n; i++){
            best = Math.max(best, 1 + helper(nums, i, i-1));
        }

        return best;
    }

    public int helper(int[] nums, int i, int j){
        if(j < 0){
            return 0;
        }

        int include = 0;
        if(nums[j] < nums[i]){
            include = 1 + helper(nums, j, j-1);
        }
        int exclude = helper(nums, i, j-1);

        return Math.max(include, exclude);
    }
}
