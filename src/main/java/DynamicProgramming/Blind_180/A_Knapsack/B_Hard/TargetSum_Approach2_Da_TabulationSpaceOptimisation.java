package DynamicProgramming.Blind_180.A_Knapsack.B_Hard;

public class TargetSum_Approach2_Da_TabulationSpaceOptimisation {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for(int i=0; i<n; i++){
            total += nums[i];
        }

        if(Math.abs(target) > total){
            return 0;
        }

        int redEqSum = total + target;
        if(redEqSum < 0 || (redEqSum % 2) != 0){
            return 0;
        }

        int p = redEqSum/2;
        int[] prev = new int[p+1];
        int[] curr = new int[p+1];

        prev[0] = 1;
        for(int i=1; i<=n; i++){
            for(int j=0; j<=p; j++){
                int pick = 0;
                if(nums[i-1] <= j){
                    pick = prev[j - nums[i-1]];
                }
                int notPick = prev[j];

                curr[j] = pick + notPick;
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;

        }

        return prev[p];
    }
}
