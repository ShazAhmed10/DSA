package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P03_TargetSum;

public class Approach1_D_TabulationSpaceOptimization {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        if(Math.abs(target) > totalSum){
            return 0;
        }

        int[] prev_arr = new int[2 * totalSum + 1];
        int[] curr_arr = new int[2 * totalSum + 1];

        for(int curr = -1 * totalSum; curr <= totalSum; curr++){
            if(curr == target){
                prev_arr[curr + totalSum] = 1;
            }
        }

        for(int i=1; i<=n; i++){
            for(int curr = -1 * totalSum; curr <= totalSum; curr++){
                int index1 = (curr - nums[i-1]) + totalSum;
                int way1 = 0;
                if(index1 >= 0 && index1 <= 2*totalSum){
                    way1 = prev_arr[index1];
                }

                int index2 = curr + nums[i-1] + totalSum;
                int way2 = 0;
                if(index2 >= 0 && index2 <= 2*totalSum){
                    way2 = prev_arr[index2];
                }

                curr_arr[curr + totalSum] = way1 + way2;
            }
            int[] temp = prev_arr;
            prev_arr = curr_arr;
            curr_arr = temp;
        }

        return prev_arr[totalSum];
    }

    public static void main(String[] args) {
        Approach1_D_TabulationSpaceOptimization solution = new Approach1_D_TabulationSpaceOptimization();
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3) + " (expected 5)");
        System.out.println(solution.findTargetSumWays(new int[]{1}, 1) + " (expected 1)");
        System.out.println(solution.findTargetSumWays(new int[]{0, 0}, 0) + " (expected 4)");
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1}, 2) + " (expected 0)");
    }
}
