package DynamicProgramming.Blind_180.C_Sequence.B_Hard.P01_LongestIncreasingSubsequence;

public class Approach1_Da_TabulationSpaceOptimisation {
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

    public static void main(String[] args) {
        Approach1_Da_TabulationSpaceOptimisation solution = new Approach1_Da_TabulationSpaceOptimisation();
        System.out.println(solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) + " (expected 4)");
        System.out.println(solution.lengthOfLIS(new int[]{7, 7, 7, 7}) + " (expected 1)");
    }
}
