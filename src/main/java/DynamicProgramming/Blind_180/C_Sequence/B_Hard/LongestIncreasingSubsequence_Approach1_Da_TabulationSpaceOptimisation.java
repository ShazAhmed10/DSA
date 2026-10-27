package DynamicProgramming.Blind_180.C_Sequence.B_Hard;

public class LongestIncreasingSubsequence_Approach1_Da_TabulationSpaceOptimisation {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] ahead = new int[n+1];
        int[] curr = new int[n+1];

        for(int curr_index = n-1; curr_index >= 0; curr_index--){
            for(int last_index = -1; last_index < curr_index; last_index++){
                int pick = 0;
                if(last_index == -1 || nums[last_index] < nums[curr_index]){
                    pick = 1 + ahead[curr_index + 1];
                }
                int notPick = ahead[last_index + 1];

                curr[last_index + 1] = Math.max(pick, notPick);
            }

            int[] temp = ahead;
            ahead = curr;
            curr = temp;
        }

        return ahead[-1 + 1];
    }
}
