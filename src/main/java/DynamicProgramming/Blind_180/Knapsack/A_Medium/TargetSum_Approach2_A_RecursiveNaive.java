package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class TargetSum_Approach2_A_RecursiveNaive {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for(int i=0; i<n; i++){
            total += nums[i];
        }

        int redEqSum = total + target;
        if(redEqSum < 0 || (redEqSum % 2) != 0){
            return 0;
        }

        return helper(nums, n, redEqSum/2);
    }

    public int helper(int[] nums, int n, int p){
        if(n == 0){
            return p == 0 ? 1 : 0;
        }

        int pick = 0;
        if(nums[n-1] <= p){
            pick = helper(nums, n-1, p - nums[n-1]);
        }
        int notPick = helper(nums, n-1, p);

        return pick + notPick;
    }
}
