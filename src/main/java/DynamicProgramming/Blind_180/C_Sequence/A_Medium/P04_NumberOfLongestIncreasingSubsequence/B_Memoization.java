package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P04_NumberOfLongestIncreasingSubsequence;

public class B_Memoization {
     static class Pair{
        int length;
        int count;

        Pair(int length, int count){
            this.length = length;
            this.count = count;
        }
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        Pair best = new Pair(0,0);
        Pair[] dp = new Pair[n];

        for(int i=0; i<n; i++){
            Pair currPair = helper(nums, i, dp);

            if(best.length < currPair.length){
                best.length = currPair.length;
                best.count = currPair.count;
            }
            else if(best.length == currPair.length){
                best.count += currPair.count;
            }
        }

        return best.count;
    }

    public Pair helper(int[] nums, int i, Pair[] dp){
        if(dp[i] != null){
            return dp[i];
        }

        Pair maxPair = new Pair(1, 1);

        for(int j=0; j<i; j++){
            if(nums[j] < nums[i]){
                Pair currPair = helper(nums, j, dp);
                int currLength = 1 + currPair.length;

                if(maxPair.length < currLength){
                    maxPair.length = currLength;
                    maxPair.count = currPair.count;
                }
                else if(maxPair.length == currLength){
                    maxPair.count += currPair.count;
                }
            }
        }

        return dp[i] = maxPair;
    }

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}) + " (expected 2)");
        System.out.println(solution.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}) + " (expected 5)");
        System.out.println(solution.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}) + " (expected 3)");
    }
}
