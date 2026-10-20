package DynamicProgramming.Blind_180.Knapsack.B_Hard;

public class TargetSum_Approach1_A_RecursiveNaive {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int curr = 0;
        return helper(nums, target, n, curr);
    }

    public int helper(int[] nums, int target, int n, int curr){
        if(n == 0 && curr == target){
            return 1;
        }

        if(n == 0){
            return 0;
        }

        int way1 = helper(nums, target, n-1, curr - nums[n-1]);
        int way2 = helper(nums, target, n-1, curr + nums[n-1]);

        return way1 + way2;
    }
}
