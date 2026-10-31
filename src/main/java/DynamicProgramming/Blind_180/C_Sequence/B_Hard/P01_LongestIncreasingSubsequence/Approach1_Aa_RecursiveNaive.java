package DynamicProgramming.Blind_180.C_Sequence.B_Hard.P01_LongestIncreasingSubsequence;

public class Approach1_Aa_RecursiveNaive {
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

    public static void main(String[] args) {
        Approach1_Aa_RecursiveNaive solution = new Approach1_Aa_RecursiveNaive();
        System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{7, 7, 7, 7}) + " (expected 1)");
    }
}
