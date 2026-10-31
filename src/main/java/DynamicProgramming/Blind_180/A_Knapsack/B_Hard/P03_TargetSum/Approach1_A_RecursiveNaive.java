package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P03_TargetSum;

public class Approach1_A_RecursiveNaive {
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

    public static void main(String[] args) {
        Approach1_A_RecursiveNaive solution = new Approach1_A_RecursiveNaive();
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3) + " (expected 5)");
        System.out.println(solution.findTargetSumWays(new int[]{1}, 1) + " (expected 1)");
        System.out.println(solution.findTargetSumWays(new int[]{0, 0}, 0) + " (expected 4)");
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1}, 2) + " (expected 0)");
    }
}
