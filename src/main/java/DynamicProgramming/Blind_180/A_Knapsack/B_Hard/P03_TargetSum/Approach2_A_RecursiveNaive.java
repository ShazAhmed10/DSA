package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P03_TargetSum;

public class Approach2_A_RecursiveNaive {
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

    public static void main(String[] args) {
        Approach2_A_RecursiveNaive solution = new Approach2_A_RecursiveNaive();
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3) + " (expected 5)");
        System.out.println(solution.findTargetSumWays(new int[]{1}, 1) + " (expected 1)");
        System.out.println(solution.findTargetSumWays(new int[]{0, 0}, 0) + " (expected 4)");
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1}, 2) + " (expected 0)");
    }
}
