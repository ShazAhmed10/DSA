package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestIncreasingSubsequence_Approach1_Aa_RecursiveNaive {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        return helper(nums, 0, -1);
    }

    public int helper(int[] nums, int curr_index, int last_index){
        if(curr_index == nums.length){
            return 0;
        }

        int pick = 0;
        if(last_index == -1 || nums[last_index] < nums[curr_index]){
            pick = 1 + helper(nums, curr_index + 1, curr_index);
        }
        int notPick = helper(nums, curr_index + 1, last_index);

        return Math.max(pick, notPick);
    }
}
